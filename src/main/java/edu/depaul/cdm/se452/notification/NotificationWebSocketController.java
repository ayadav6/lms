package edu.depaul.cdm.se452.notification;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class NotificationWebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationWebSocketController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendNotification(String userId, String message) {
        if (userId == null || userId.isEmpty() || message == null || message.isEmpty()) {
            logger.warn("Invalid input: userId='{}', message='{}'", userId, message);
            return;
        }
        logger.info("Sending notification to userId='{}' with message='{}'", userId, message);
        messagingTemplate.convertAndSendToUser(userId, "/topic/notifications", message);
        logger.debug("Notification sent successfully to userId='{}'", userId);
    }
}
