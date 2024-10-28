package edu.depaul.cdm.se452.discussionforum;



import edu.depaul.cdm.se452.user.User;

import edu.depaul.cdm.se452.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private UserRepository userRepository;

    public Comment addComment(String content, Long userId, Long discussionId) {

        User createdBy = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Discussion discussion = discussionRepository.findById(discussionId).orElseThrow(() -> new IllegalArgumentException("Discussion not found"));


        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreatedBy(createdBy);
        comment.setDiscussion(discussion);

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByDiscussionId(Long discussionId) {
        return commentRepository.findByDiscussionId(discussionId);
    }
}
