package edu.depaul.cdm.se452.assessment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository  extends JpaRepository<Grade, Long> {
       // Find grades by submission ID
    List<Grade> findBySubmissionId(Long submissionId);
}