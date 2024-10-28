package edu.depaul.cdm.se452.assessment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

import java.util.List;


@RestController
@RequestMapping("/api/submissions")
@Tag(name = "Submissions", description = "Everything about Submission")
@Log4j2
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Submit an Assignment")
    public ResponseEntity<SubmissionDTO> createSubmission(
            @RequestParam("studentId") Long studentId,
            @RequestParam("assignmentId") Long assignmentId,
            @RequestParam(value = "comment", required = false) String comment,
            @RequestParam("file") MultipartFile file) {
        log.traceEntry("Entering createSubmission with studentId: {}, assignmentId: {}, comment: {}", studentId, assignmentId, comment);
        SubmissionDTO submissionDTO = new SubmissionDTO();
        submissionDTO.setStudentId(studentId);
        submissionDTO.setAssignmentId(assignmentId);

        if (comment != null && !comment.isEmpty()) {
            submissionDTO.setComment(comment);
        }
     
        SubmissionDTO savedSubmission = submissionService.createSubmissionWithFile(submissionDTO, file);
        log.info("Submission created with ID: {}", savedSubmission.getId());
        log.traceExit("Exiting createSubmission with savedSubmission: {}", savedSubmission);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubmission);
    }

    @GetMapping("/assignment/{assignmentId}")
    @Operation(summary = "Get All Submissions for an Assignment")
    public ResponseEntity<List<SubmissionDTO>> getSubmissionsByAssignmentId(@PathVariable Long assignmentId) {
        log.traceEntry("Entering getSubmissionsByAssignmentId with assignmentId: {}", assignmentId);
        List<SubmissionDTO> submissions = submissionService.getAllSubmissionsByAssignmentId(assignmentId);
        log.info("Retrieved {} submissions for assignment ID: {}", submissions.size(), assignmentId);
        log.traceExit("Exiting getSubmissionsByAssignmentId with submissions list");
        return ResponseEntity.ok(submissions);
    }
   
    
    @GetMapping("/{studentId}/{assignmentId}")
    @Operation(summary = "Get Submission Details of particular Student and assignment")
    public ResponseEntity<SubmissionDTO> getSubmissionWithFileDownloadLink(
    
            @PathVariable Long studentId,
            @PathVariable Long assignmentId) {
        log.traceEntry("Entering getSubmissionWithFileDownloadLink with studentId: {}, assignmentId: {}", studentId, assignmentId);
        SubmissionDTO submissionDTO = submissionService.getSubmissionByStudentAndAssignment(studentId, assignmentId);
        if (submissionDTO.getFileName() != null) {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/submissions/download/")
                    .path(submissionDTO.getId().toString())
                    .toUriString();
            submissionDTO.setFileDownloadUrl(fileDownloadUri);
        }
        log.info("Retrieved submission for student ID: {}, assignment ID: {}", studentId, assignmentId);
        log.traceExit("Exiting getSubmissionWithFileDownloadLink with submissionDTO: {}", submissionDTO);
        
        return ResponseEntity.ok(submissionDTO);
    }
  
    @GetMapping("/download/{id}")
    @Operation(summary = "Download the submission File")
    public ResponseEntity<Resource> downloadSubmissionFile(@PathVariable Long id) {
        log.traceEntry("Entering downloadSubmissionFile with ID: {}", id);
        
        Submission submission = submissionService.getSubmissionById(id);
        Resource resource = new ByteArrayResource(submission.getFileData());
        log.info("Downloading file for submission ID: {}", id);
        log.traceExit("Exiting downloadSubmissionFile with resource data");
        
        return ResponseEntity.ok()
       .contentType(MediaType.APPLICATION_OCTET_STREAM)
       .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + submission.getFileName() + "\"")
       .contentLength(submission.getFileData().length) // Set the content length
       .body(resource);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Submission")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
        log.traceEntry("Entering deleteSubmission with ID: {}", id);
        submissionService.deleteSubmission(id);
        log.info("Submission with ID: {} deleted successfully", id);
        log.traceExit("Exiting deleteSubmission");
        return ResponseEntity.noContent().build();
    } 
}
