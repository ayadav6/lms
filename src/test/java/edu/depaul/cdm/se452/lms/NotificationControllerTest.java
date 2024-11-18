package edu.depaul.cdm.se452.notification;

import edu.depaul.cdm.se452.assessment.Assignment;
import edu.depaul.cdm.se452.assessment.Grade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendAssignmentNotification() {
        Assignment assignment = new Assignment();
        assignment.setTitle("Test Assignment");
        Long courseId = 1L;

        String response = notificationController.sendAssignmentNotification(assignment, courseId);

        assertEquals("Assignment notifications sent.", response);
        verify(notificationService, times(1)).sendAssignmentNotification(assignment, courseId);
    }

    @Test
    void testSendGradeNotification() {
        Grade grade = new Grade();
        grade.setId(1L);

        String response = notificationController.sendGradeNotification(grade);

        assertEquals("Grade notification sent.", response);
        verify(notificationService, times(1)).sendGradeNotification(grade);
    }
}
