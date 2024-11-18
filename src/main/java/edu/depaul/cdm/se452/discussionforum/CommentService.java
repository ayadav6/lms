package edu.depaul.cdm.se452.discussionforum;

import edu.depaul.cdm.se452.user.User;
import edu.depaul.cdm.se452.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentJpaRepository commentJpaRepository;
    private final CommentMongoRepository commentMongoRepository;
    private final DiscussionJpaRepository discussionJpaRepository;
    private final DiscussionMongoRepository discussionMongoRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(
            CommentJpaRepository commentJpaRepository,
            CommentMongoRepository commentMongoRepository,
            DiscussionJpaRepository discussionJpaRepository,
            DiscussionMongoRepository discussionMongoRepository,
            UserRepository userRepository) {
        this.commentJpaRepository = commentJpaRepository;
        this.commentMongoRepository = commentMongoRepository;
        this.discussionJpaRepository = discussionJpaRepository;
        this.discussionMongoRepository = discussionMongoRepository;
        this.userRepository = userRepository;
    }

    public void addComment(String content, Long createdByUserId, Long discussionId, boolean useMongo) {
        if (useMongo) {
            CommentMongo comment = new CommentMongo();
            comment.setContent(content);
            comment.setCreatedByUserId(createdByUserId);
            comment.setDiscussionId(discussionId);
            commentMongoRepository.save(comment);
        } else {
            User createdBy = userRepository.findById(createdByUserId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            DiscussionJpa discussion = discussionJpaRepository.findById(discussionId)
                    .orElseThrow(() -> new IllegalArgumentException("Discussion not found"));

            CommentJpa comment = new CommentJpa();
            comment.setContent(content);
            comment.setCreatedBy(createdBy);
            comment.setDiscussion(discussion);

            commentJpaRepository.save(comment);
        }
    }

    public List<?> getCommentsByDiscussionId(Long discussionId, boolean useMongo) {
        if (useMongo) {
            return commentMongoRepository.findByDiscussionId(discussionId);
        } else {
            return commentJpaRepository.findByDiscussionId(discussionId);
        }
    }

}
