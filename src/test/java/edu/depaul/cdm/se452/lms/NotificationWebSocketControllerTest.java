package edu.depaul.cdm.se452.notification;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.Mockito.*;

class NotificationWebSocketControllerTest {

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private NotificationWebSocketController notificationWebSocketController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendWebSocketNotification() {
        String userId = "123";
        String message = "Test Notification";

        notificationWebSocketController.sendNotification(userId, message);

        verify(messagingTemplate, times(1)).convertAndSendToUser(eq(userId), eq("/topic/notifications"), eq(message));
    }

    @Test
    void testSendWebSocketNotificationWithInvalidUserId() {
        String userId = null;
        String message = "Test Notification";

        notificationWebSocketController.sendNotification(userId, message);

        verify(messagingTemplate, never()).convertAndSendToUser(anyString(), anyString(), anyString());
    }

    @Test
    void testSendWebSocketNotificationWithEmptyMessage() {
        String userId = "123";
        String message = "";

        notificationWebSocketController.sendNotification(userId, message);

        verify(messagingTemplate, never()).convertAndSendToUser(anyString(), anyString(), anyString());
    }

}

