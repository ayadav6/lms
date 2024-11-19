package edu.depaul.cdm.se452.assessment;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StudentReviewServiceNosql {
    @Autowired
    public StudentReviewRepository reviewRepository;



    public List<StudentReviewNosql> getReviewsByAssignment(String assignmentId) {
        return reviewRepository.findByAssignmentId(assignmentId);
    }

    public StudentReviewNosql addStudentReview(StudentReviewNosql review) {       
        review.setTime(System.currentTimeMillis());
        List<StudentReviewNosql> allReviews = reviewRepository.findAll();
        // Get the highest numeric ID (or set it to 0 if no reviews exist)
        long maxId = allReviews.stream()
            .mapToLong(r -> {
            try {
                 return Long.parseLong(r.getId());
                } catch (NumberFormatException e) {
             return 0; // Handle invalid IDs gracefully
            }
            })
     .max()
     .orElse(0);
    
     review.setId(String.valueOf(maxId+1));
    return reviewRepository.save(review);
    }

    public void deleteReview(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }


}
