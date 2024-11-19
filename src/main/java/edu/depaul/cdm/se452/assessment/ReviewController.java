

package edu.depaul.cdm.se452.assessment;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student-reviews")
public class ReviewController {

@Autowired
private StudentReviewServiceNosql reviewService;


// Show the review form for a specific assignment
@GetMapping("/new/{id}")
public String reviewForm(@PathVariable("id") Long assignmentId, Model model) {
model.addAttribute("assignmentId", assignmentId);
return "review/reviews"; // This renders the reviews.html form
}

// Handle form submissions from the web
@PostMapping("/submit")
public String addReview(@ModelAttribute StudentReviewNosql review, Model model) {
reviewService.addStudentReview(review); // Save the review
return "redirect:/student-reviews/thank-you"; // Redirect to a thank you page
}

// Thank you page after submission (optional)
@GetMapping("/thank-you")
public String thankYouPage() {
return "review/thank-you"; // Redirect to a "Thank you" page after review submission
}


}

