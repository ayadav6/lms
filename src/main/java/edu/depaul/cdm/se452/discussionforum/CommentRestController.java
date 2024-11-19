package edu.depaul.cdm.se452.discussionforum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {

    @Autowired
    private CommentMongoRepository commentMongoRepository;

    // Create a new comment
    @PostMapping
    public CommentMongo createComment(@RequestBody CommentMongo comment) {
        return commentMongoRepository.save(comment);
    }

    // Get all comments
    @GetMapping
    public List<CommentMongo> getAllComments() {
        return commentMongoRepository.findAll();
    }

    // Get comments by discussion ID
    @GetMapping("/discussion/{discussionId}")
    public List<CommentMongo> getCommentsByDiscussionId(@PathVariable Long discussionId) {
        return commentMongoRepository.findByDiscussionId(discussionId);
    }

    // Delete a comment by ID
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable String id) {
        commentMongoRepository.deleteById(id);
    }
}

