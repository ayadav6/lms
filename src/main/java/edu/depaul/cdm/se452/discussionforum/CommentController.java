package edu.depaul.cdm.se452.discussionforum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Flag to toggle between JPA (H2) and MongoDB
    private boolean useMongo = false;

    public void setUseMongo(boolean useMongo) {
        this.useMongo = useMongo;
    }

    @PostMapping("/add")
    public String addComment(@ModelAttribute("commentForm") CommentDTO commentForm) {
        commentService.addComment(
                commentForm.getContent(),
                commentForm.getCreatedByUserId(),
                commentForm.getDiscussionId(),
                useMongo
        );
        return "redirect:/discussions/" + commentForm.getDiscussionId();
    }

    @GetMapping("/discussion/{discussionId}")
    public String getCommentsByDiscussionId(@PathVariable Long discussionId, Model model) {
        List<?> comments = commentService.getCommentsByDiscussionId(discussionId, useMongo);
        model.addAttribute("comments", comments);
        return "comment/comments";
    }
}
