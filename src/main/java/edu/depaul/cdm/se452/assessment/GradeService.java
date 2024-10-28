package edu.depaul.cdm.se452.assessment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.depaul.cdm.se452.user.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private UserRepository userRepository; // Assuming you have a user repository

    public GradeDTO createGrade(GradeDTO gradeDTO) {
        Grade grade = new Grade();
        grade.setGradeValue(gradeDTO.getGradeValue());
        grade.setFeedback(gradeDTO.getFeedback());
        grade.setGradedAt(new Timestamp(System.currentTimeMillis()));
      

        // Fetch submission and instructor from repositories
        Submission submission = submissionRepository.findById(gradeDTO.getSubmissionId())
                .orElseThrow(() -> new RuntimeException("Submission not found"));
        grade.setSubmission(submission);
        

        User instructor = userRepository.findById(gradeDTO.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        grade.setInstructor(instructor);

        submission.setGradeValue(grade.getGradeValue());
        submission.setGraded(true);
        submissionRepository.save(submission);

        Grade savedGrade = gradeRepository.save(grade);
        return convertToDTO(savedGrade);
    }

    public GradeDTO getGradeById(Long id) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found"));
        return convertToDTO(grade);
    }

    public List<GradeDTO> getAllGrades() {
        return gradeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public GradeDTO updateGrade(Long id, GradeDTO gradeDTO) {
        Grade existingGrade = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found"));

        // Update fields
        if (gradeDTO.getGradeValue() != null) {
            
            Submission submission = existingGrade.getSubmission();
            submission.setGradeValue(gradeDTO.getGradeValue());
            submission.setGraded(true);
            submissionRepository.save(submission);
        }
        if (gradeDTO.getFeedback() != null) {
            existingGrade.setFeedback(gradeDTO.getFeedback());
        }
        existingGrade.setGradedAt(gradeDTO.getGradedAt());
        existingGrade.setGradedAt(new Timestamp(System.currentTimeMillis()));
        Grade updatedGrade = gradeRepository.save(existingGrade);
        return convertToDTO(updatedGrade);
    }

    public void deleteGrade(Long id) {
        Grade grade = gradeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Grade not found"));

        Submission submission = grade.getSubmission();
        submission.setGradeValue(null);
        submission.setGraded(false);  // Reset graded status
        submissionRepository.save(submission);
        gradeRepository.deleteById(id);
    }

    private GradeDTO convertToDTO(Grade grade) {
        GradeDTO dto = new GradeDTO();
        dto.setId(grade.getId());
        dto.setSubmissionId(grade.getSubmission().getId());
        dto.setInstructorId(grade.getInstructor().getId());
        dto.setGradeValue(grade.getGradeValue());
        dto.setFeedback(grade.getFeedback());
        dto.setGradedAt(grade.getGradedAt());
        return dto;
    }
}
