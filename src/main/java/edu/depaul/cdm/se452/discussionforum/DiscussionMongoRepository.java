package edu.depaul.cdm.se452.discussionforum;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiscussionMongoRepository extends MongoRepository<DiscussionMongo, String> {
}
