package edu.depaul.cdm.se452.discussionforum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public String addComment(@ModelAttribute("commentForm") CommentDTO commentForm) {
        commentService.addComment(commentForm.getContent(), commentForm.getCreatedByUserId(), commentForm.getDiscussionId());
        return "redirect:/discussion/" + commentForm.getDiscussionId();  // Redirect to the discussion page
    }


    @GetMapping("/discussion/{discussionId}")
    public String getCommentsByDiscussionId(@PathVariable Long discussionId, Model model) {
        List<Comment> comments = commentService.getCommentsByDiscussionId(discussionId);
        model.addAttribute("comments", comments); // Add list of comments to the model
        return "comment/comments"; // This should match your Thymeleaf template name
    }
}
