package edu.depaul.cdm.se452.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByStudentIdAndAssignmentId(Long studentId, Long assignmentId);
    
    List<Submission> findByAssignmentId(Long assignmentId);
    
}