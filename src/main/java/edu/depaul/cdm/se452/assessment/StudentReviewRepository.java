package edu.depaul.cdm.se452.assessment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentReviewRepository extends MongoRepository<StudentReviewNosql, String> {
    List<StudentReviewNosql> findByAssignmentId(String assignmentId);
}
