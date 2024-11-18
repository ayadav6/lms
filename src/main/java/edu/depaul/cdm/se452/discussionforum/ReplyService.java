package edu.depaul.cdm.se452.discussionforum;

import edu.depaul.cdm.se452.user.User;
import edu.depaul.cdm.se452.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyJpaRepository replyJpaRepository;

    @Autowired
    private ReplyMongoRepository replyMongoRepository;

    @Autowired
    private CommentJpaRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    private final boolean useMongoDb = true; // Flag to switch between databases

    public ReplyJpa addReply(String content, Long createdByUserId, Long commentId) {
        // Find the User who created the reply
        User createdBy = userRepository.findById(createdByUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Find the Comment associated with the reply
        CommentJpa comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        // Create and save the new Reply
        ReplyJpa reply = new ReplyJpa(content, createdBy, comment);

        if (useMongoDb) {
            return replyMongoRepository.save(reply);
        } else {
            return replyJpaRepository.save(reply);
        }
    }

    public List<ReplyJpa> getRepliesByCommentId(Long commentId) {
        if (useMongoDb) {
            return replyMongoRepository.findByComment_Id(commentId);
        } else {
            return replyJpaRepository.findByComment_Id(commentId);
        }
    }
}
