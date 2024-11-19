package edu.depaul.cdm.se452.assessment;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.depaul.cdm.se452.user.User;
import edu.depaul.cdm.se452.user.UserRepository;
import java.util.stream.Collectors;
import java.io.IOException;

import java.sql.Timestamp;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final AssignmentRepository assignmentRepository;
   
    @Autowired
    public SubmissionService(SubmissionRepository submissionRepository,
                             UserRepository userRepository,
                             AssignmentRepository assignmentRepository) {
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
        this.assignmentRepository = assignmentRepository;

    }

    //Creates a submission
    public SubmissionDTO createSubmissionWithFile(SubmissionDTO submissionDTO, MultipartFile file) {
        
        
        Submission submission = new Submission();
        User student = userRepository.findById(submissionDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Assignment assignment = assignmentRepository.findById(submissionDTO.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
       // String fileName = saveFile(file);
         submission.setStudent(student);
        submission.setAssignment(assignment);
        submission.setComment(submissionDTO.getComment());
        submission.setFileName(file.getOriginalFilename());
        submission.setSubmittedAt(new Timestamp(System.currentTimeMillis()));
        submission.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        try {
            // Store file content as binary data in the database
            submission.setFileData(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file data", e);
        }
        Submission savedSubmission = submissionRepository.save(submission);
        return convertToDTO(savedSubmission);
    }

    //Fetch submission with studentId and AssignmentId
    public SubmissionDTO getSubmissionByStudentAndAssignment(Long studentId, Long assignmentId) {
       Submission submission = submissionRepository.findByStudentIdAndAssignmentId(studentId, assignmentId)
        .stream()
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Submission not found for student ID " + studentId + " and assignment ID " + assignmentId));
    
        return convertToDTO(submission);
       
    }
    //Delete submission
    public void deleteSubmission(Long id) {
        if (!submissionRepository.existsById(id)) {
            throw new RuntimeException("Submission not found with ID " + id);
        }
          submissionRepository.deleteById(id);
    }

    //Fetch submission
    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
    }

    //Upload submission
    public Resource loadFileAsResource(Long id) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
               
        Resource resource = new ByteArrayResource(submission.getFileData());
        return resource;
    }

    //Fetch all submissions for assignment
    public List<SubmissionDTO> getAllSubmissionsByAssignmentId(Long assignmentId) {
      List<Submission> submissions = submissionRepository.findByAssignmentId(assignmentId);

        // Convert to DTO and return
        return submissions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SubmissionDTO convertToDTO(Submission submission) {
        SubmissionDTO submissionDTO = new SubmissionDTO();
        submissionDTO.setId(submission.getId());
        submissionDTO.setStudentId(submission.getStudent().getId());
        submissionDTO.setAssignmentId(submission.getAssignment().getId());
        submissionDTO.setComment(submission.getComment());
        submissionDTO.setFileName(submission.getFileName());
        submissionDTO.setCreatedAt(submission.getSubmittedAt());
        submissionDTO.setUpdatedAt(submission.getUpdatedAt());
        
        return submissionDTO;

    }
}