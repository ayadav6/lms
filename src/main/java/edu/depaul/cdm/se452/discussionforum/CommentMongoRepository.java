package edu.depaul.cdm.se452.discussionforum;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentMongoRepository extends MongoRepository<CommentMongo, String> {
    List<CommentMongo> findByDiscussionId(Long discussionId);
}
