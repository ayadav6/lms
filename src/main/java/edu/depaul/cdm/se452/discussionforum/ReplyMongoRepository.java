package edu.depaul.cdm.se452.discussionforum;


import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ReplyMongoRepository extends MongoRepository<ReplyJpa, String> {
    List<ReplyJpa> findByComment_Id(Long commentId); // Match Comment's ID type
}


