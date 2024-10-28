package edu.depaul.cdm.se452.assessment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import edu.depaul.cdm.se452.course.Course;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;

    // Retrieve all assignments
    public List<Assignment> getAllAssignments() {
        log.traceEntry("Getting all assignments");
        List<Assignment> assignments = assignmentRepository.findAll();
        log.traceExit("All assignments retrieved: {}", assignments);
        return assignments;
    }

    public Optional<Assignment> getAssignmentById(Long id) {
        log.traceEntry("Getting assignment with id: {}", id);
        Optional<Assignment> assignment = assignmentRepository.findById(id);
        if (assignment.isPresent()) {
            log.traceExit("Assignment found: {}", assignment.get());
            } else {
                log.warn("Assignment not found with id: {}", id);
            }
            return assignment;
    }


    public Assignment createAssignment(Assignment assignment) {
        log.traceEntry("Creating a new assignment: {}", assignment);
    
        // Check if ID exists in the request and if it’s already present in the database
        if (assignment.getId() != null) {
            Optional<Assignment> existingAssignment = assignmentRepository.findById(assignment.getId());
            if (existingAssignment.isPresent()) {
                throw new IllegalArgumentException("Assignment with ID " + assignment.getId() + " already exists.");
            }
        }
    
        // Set timestamps for creation and update
        assignment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        assignment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    
        Assignment savedAssignment = assignmentRepository.save(assignment);
        log.traceExit("Assignment created: {}", savedAssignment);
        return savedAssignment;
    }

    // Upload a file for a specific assignment
    public void uploadFile(Long assignmentId, MultipartFile file) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
            .orElseThrow(() -> new RuntimeException("Assignment not found with ID: " + assignmentId));
        
        try {
            assignment.setFileName(file.getOriginalFilename());
            assignment.setFileType(file.getContentType());
            assignment.setFileData(file.getBytes());
            assignmentRepository.save(assignment);
        } catch (IOException e) {
            throw new RuntimeException("Error storing file data: " + e.getMessage(), e);
        }
    }

    // Update an existing assignment (optional)
    public Assignment updateAssignment(Long id, Assignment updatedAssignment) {
        log.traceEntry("Updating assignment with id: {}", id);
        return assignmentRepository.findById(id)
                .map(assignment -> {
                    assignment.setTitle(updatedAssignment.getTitle());
                    assignment.setDescription(updatedAssignment.getDescription());
                    assignment.setDueDate(updatedAssignment.getDueDate());
                    assignment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    log.info("Assignment with id: {} updated with new data: {}", id, updatedAssignment);
                    return assignmentRepository.save(assignment);
                }).orElseThrow(() -> new RuntimeException("Assignment not found with id " + id));
    }

    public List<AssignmentDTO> getAssignmentsByCourseName(String courseName) {
        return assignmentRepository.findAll()
                .stream()
                .filter(assignment -> courseName.equalsIgnoreCase(assignment.getCourse().getName())) // assuming course has a getName method
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    
            
    // Delete an assignment by id
    public void deleteAssignment(Long id) {
        log.traceEntry("Deleting assignment with id: {}", id);
        assignmentRepository.deleteById(id);
        log.traceExit("Assignment with id: {} deleted", id);
    } 

    public AssignmentDTO convertToDTO(Assignment assignment) {
        Course course = assignment.getCourse();
        String courseName = course != null ? course.getName() : null;
        AssignmentDTO dto = new AssignmentDTO();
        dto.setId(assignment.getId());
        dto.setTitle(assignment.getTitle());
        dto.setDescription(assignment.getDescription());
        dto.setCourseName(courseName);
        dto.setDueDate(assignment.getDueDate());
        dto.setCreatedAt(assignment.getCreatedAt());
        dto.setUpdatedAt(assignment.getUpdatedAt());
        return dto;
    }
}

