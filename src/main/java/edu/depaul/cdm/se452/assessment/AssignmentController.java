package edu.depaul.cdm.se452.assessment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Controller
@RequestMapping("/api/assignments")
@Tag(name = "Assignment", description = "Everything about Assignment")
@Log4j2
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    //Fetch all assignments from the database and Converts Assignment entities to DTOs for the response.
    @GetMapping
    @Operation(summary = "Returns all Assignments in the database")
    @ApiResponse(responseCode = "200", description = "Valid response",
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Assignment.class))})
    public ResponseEntity<List<AssignmentDTO>> getAllAssignments() {
        log.info("Request received to fetch all assignments");
        List<AssignmentDTO> assignmentDTOs = assignmentService.getAllAssignments()
                .stream()
                .map(assignmentService::convertToDTO)
                .collect(Collectors.toList());
        log.info("Returning {} assignments", assignmentDTOs.size());
        return ResponseEntity.ok(assignmentDTOs);
    }

    //Fetch an assignment by ID. If a file is attached, it allows downloading the file;  otherwise, it returns assignment details.
   @GetMapping("/{id}")
    @Operation(summary = "Download files related to an Assignment")
    public ResponseEntity<?> getAssignmentById(@PathVariable Long id) {
        log.info("Request received to fetch assignment with ID: {}", id);
        Optional<Assignment> optionalAssignment = assignmentService.getAssignmentById(id);
        if (optionalAssignment.isPresent()) {
            Assignment assignment = optionalAssignment.get();
            if (assignment.getFileData() != null && assignment.getFileData().length > 0) {
                log.info("Assignment with ID: {} has a file attached, preparing for download", id);
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(assignment.getFileType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + assignment.getFileName() + "\"")
                        .body(new ByteArrayResource(assignment.getFileData()));
            } else {
                log.info("Assignment with ID: {} has no file, returning assignment details only", id);
                return ResponseEntity.ok(assignmentService.convertToDTO(assignment));
            }
        } else {
            log.warn("Assignment with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }
    
    // Fetch assignments by course name.
    @GetMapping("/course/{courseName}")
    @Operation(summary = "Get all Assignments related to a specific Course")
    public ResponseEntity<List<AssignmentDTO>> getAssignmentsByCourseName(@PathVariable String courseName) {
        log.info("Request received to fetch assignments for course: {}", courseName);
        List<AssignmentDTO> assignments = assignmentService.getAssignmentsByCourseName(courseName);
        log.info("Returning {} assignments for course: {}", assignments.size(), courseName);
        return ResponseEntity.ok(assignments);
    }

    //Create a new assignment and return its ID.
    @PostMapping
    @Operation(summary = "Create a new Assignment and return its ID")
    public ResponseEntity<Long> createAssignment(
        @Parameter(description = "Assignment details", required = true) @Valid @RequestBody Assignment assignment) {
            log.traceEntry("Enter createAssignment", assignment);
            Assignment createdAssignment = assignmentService.createAssignment(assignment);
            log.traceExit("Exit createAssignment with new id: {}", createdAssignment.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAssignment.getId());
    }

    //Upload assignment files for a particular assignment
    @PostMapping(value = "/upload/{assignmentId}", consumes = {"multipart/form-data"})
    @Operation(summary = "Upload a file for a specific Assignment")
    public ResponseEntity<Void> uploadFile(
        @PathVariable Long assignmentId,
        @RequestParam("file") MultipartFile file) {
            log.info("Request received to upload file for assignment ID: {}", assignmentId);
            assignmentService.uploadFile(assignmentId, file);
            log.info("File uploaded for assignment ID: {}", assignmentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Update an assignment
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Assignment")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @Valid @RequestBody Assignment updatedAssignment) {
        log.info("Request received to update assignment with ID: {}", id);
        return ResponseEntity.ok(assignmentService.updateAssignment(id, updatedAssignment));
    }

    //Delete the assignment by id
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an Assignment")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        log.traceEntry("Enter deleteAssignment with id: {}", id);
        assignmentService.deleteAssignment(id);
        log.traceExit("Exit deleteAssignment");
        return ResponseEntity.noContent().build();
    }

    //get assigmentss by Cpurse ID
    @GetMapping("/course/id/{courseId}")
    @Operation(summary = "Get Assignments by Course ID")
    public ResponseEntity<List<AssignmentDTO>> getAssignmentsByCourseId(@PathVariable Long courseId) {
        log.info("Fetching assignments for course ID: {}", courseId);
        List<AssignmentDTO> assignments = assignmentService.getAssignmentsByCourseId(courseId);
        return ResponseEntity.ok(assignments);
    }

    //Search Assignments by Title Keyword
    @GetMapping("/search/title")
    @Operation(summary = "Search Assignments by Title Keyword")
    public ResponseEntity<List<AssignmentDTO>> searchAssignmentsByTitle(@RequestParam String keyword) {
        log.info("Searching assignments with title containing: {}", keyword);
        List<AssignmentDTO> assignments = assignmentService.searchAssignmentsByTitle(keyword);
        return ResponseEntity.ok(assignments);
    }

    // Handle validation errors during API calls.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.warn("Validation error encountered: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.info("Returning validation errors: {}", errors);
        return errors;
    }



     // Thymeleaf-specific methods

    /**
     * Render the list of assignments for Thymeleaf view.
     */
    @GetMapping("/view")
    public String listAssignmentsForView(Model model) {
        List<Assignment> assignments = assignmentService.getAllAssignments(); // Ensure all fields are fetched
        model.addAttribute("assignments", assignments); // Pass the full list of assignments to the model
        return "assignment/assignments";
    }

    /**
     * Render the update form for Thymeleaf view.
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        log.info("Request received to render update form for assignment ID: {}", id);
        Optional<Assignment> assignment = assignmentService.getAssignmentById(id);
        if (assignment.isPresent()) {
            model.addAttribute("assignment", assignment.get());
            return "update-assignment"; // update-assignment.html template
        } else {
            log.warn("Assignment with ID: {} not found", id);
            return "error"; // error.html template
        }
    }

    /**
     * Process the update form submission.
     */
    @PostMapping("/update/{id}")
    public String updateAssignmentFromForm(@PathVariable Long id, @ModelAttribute("assignment") Assignment updatedAssignment) {
        log.info("Processing update form for assignment ID: {}", id);
        assignmentService.updateAssignment(id, updatedAssignment);
        return "redirect:/api/assignments/view"; // Redirect to Thymeleaf list view
    }

    /**
     * Render the create assignment form.
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        log.info("Rendering create form for a new assignment");
        model.addAttribute("assignment", new Assignment());
        return "create-assignment"; // create-assignment.html template
    }

    /**
     * Process the create form submission.
     */
    @PostMapping("/create")
    public String createAssignmentFromForm(@ModelAttribute("assignment") Assignment assignment) {
        log.info("Processing create form for a new assignment");
        assignmentService.createAssignment(assignment);
        return "redirect:/api/assignments/view"; // Redirect to Thymeleaf list view
    }

  

}
