package edu.depaul.cdm.se452.discussionforum;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentJpaRepository extends JpaRepository<CommentJpa, Long> {
    List<CommentJpa> findByDiscussionId(Long discussionId);
}
