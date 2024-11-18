package edu.depaul.cdm.se452.lms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.depaul.cdm.se452.assessment.*;

@SpringBootTest
public class StudentReviewTest {

    @Autowired
    private StudentReviewServiceNosql reviewService;

    @Autowired
    private StudentReviewRepository reviewRepository;

    private StudentReviewNosql review;

    @BeforeEach
    public void setUp() {
        // Prepare a review for testing
        String assignmentId = "assignment123";
        String studentId = "student456";
        String reviewText = "Great assignment!";
        int rating = 5;
        
        review = StudentReviewNosql.builder()
                .assignmentId(assignmentId)
                .studentId(studentId)
                .reviewText(reviewText)
                .rating(rating)
                .build();
    }

    @Test
    public void testAddReview() {
        // Add a review
        StudentReviewNosql savedReview = reviewService.addStudentReview(review);

        // Assert the review is added successfully
        Optional<StudentReviewNosql> foundReview = reviewRepository.findById(savedReview.getId());
        assertTrue(foundReview.isPresent());
        assertEquals(savedReview.getReviewText(), foundReview.get().getReviewText());
    }

    @Test
    public void testGetReviewByAssignment() {
        // Add a review first (ensure there's a review to fetch)
        reviewService.addStudentReview(review);

        // Get reviews by assignment ID
        String assignmentId = review.getAssignmentId();
        List<StudentReviewNosql> reviews = reviewService.getReviewsByAssignment(assignmentId);

        // Assert that we get the correct review
        assertTrue(reviews.size() > 0);
        assertEquals(assignmentId, reviews.get(0).getAssignmentId());
    }

    @Test
    public void testDeleteReview() {
        // Add a review first (ensure there's a review to delete)
        StudentReviewNosql savedReview = reviewService.addStudentReview(review);

        // Delete the review
        reviewService.deleteReview(savedReview.getId());

        // Assert the review is deleted
        Optional<StudentReviewNosql> deletedReview = reviewRepository.findById(savedReview.getId());
        assertFalse(deletedReview.isPresent());
    }
}
