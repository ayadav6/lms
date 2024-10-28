package edu.depaul.cdm.se452.discussionforum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@RestController
@RequestMapping("/api/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/add")
    public String addReply(@ModelAttribute("replyForm") ReplyDTO replyForm) {
        replyService.addReply(replyForm.getContent(), replyForm.getCreatedByUserId(), replyForm.getCommentId());
        return "redirect:/comment/" + replyForm.getCommentId();
    }


    @GetMapping("/comment/{commentId}")
    public String getRepliesByCommentId(@PathVariable Long commentId, Model model) {
        List<Reply> replies = replyService.getRepliesByCommentId(commentId);
        model.addAttribute("replies", replies); // Add list of replies to the model
        return "reply/replies"; // This should match your Thymeleaf template name
    }

}
