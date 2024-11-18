package edu.depaul.cdm.se452.assessment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/student-reviews")
public class StudentReviewNosqlController {

    @Autowired
    private StudentReviewServiceNosql reviewService;

    
    @PostMapping
    @Operation(summary = "Post Review for an Assignments")
    public StudentReviewNosql addReview(@RequestBody StudentReviewNosql review) {
        return reviewService.addStudentReview(review);
    }

    @GetMapping("/{assignmentId}")
    @Operation(summary = "Get Review for an assignment")
    public List<StudentReviewNosql> getReviews(@PathVariable String assignmentId) {
        return reviewService.getReviewsByAssignment(assignmentId);
    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = "Delete Review")
    public void deleteReview(@PathVariable String reviewId) {
        reviewService.deleteReview(reviewId);
    }


    


}
