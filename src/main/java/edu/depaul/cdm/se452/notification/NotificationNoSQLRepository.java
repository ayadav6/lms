package edu.depaul.cdm.se452.notification;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationNoSQLRepository extends MongoRepository<NotificationDocument, String> {
    List<NotificationDocument> findByUserId(String userId);
}
