package edu.depaul.cdm.se452.lms;

import edu.depaul.cdm.se452.user.UserRepository;
import edu.depaul.cdm.se452.assessment.Assignment;
import edu.depaul.cdm.se452.assessment.Grade;
import edu.depaul.cdm.se452.assessment.Submission;
import edu.depaul.cdm.se452.notification.*;
import edu.depaul.cdm.se452.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class NotificationServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private NotificationNoSQLRepository notificationNoSQLRepository;

    @Mock
    private NotificationWebSocketController webSocketController;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendAssignmentNotificationLogic() {
        Assignment assignment = new Assignment();
        assignment.setTitle("Test Assignment");
        Long courseId = 1L;

        User user = new User();
        user.setId(1L);

        when(userRepository.findStudentsByCourseId(courseId)).thenReturn(List.of(user));

        notificationService.sendAssignmentNotification(assignment, courseId);

        verify(userRepository, times(1)).findStudentsByCourseId(courseId);
        verify(notificationNoSQLRepository, times(1)).save(any(NotificationDocument.class));
        verify(webSocketController, times(1)).sendNotification(eq("1"), contains("New Assignment Posted"));
    }

    @Test
    void testSendGradeNotificationLogic() {

        Grade grade = new Grade();
        grade.setId(1L);

        Submission submission = new Submission();
        User student = new User();
        student.setId(1L);
        submission.setStudent(student);

        Assignment assignment = new Assignment();
        assignment.setTitle("Test Assignment");
        submission.setAssignment(assignment);

        grade.setSubmission(submission);

        notificationService.sendGradeNotification(grade);

        verify(notificationNoSQLRepository, times(1))
                .save(argThat(notification -> notification.getNotificationType().equals("grading") &&
                        notification.getUserId().equals("1") &&
                        notification.getMessage().contains("Grade Updated for: Test Assignment") &&
                        notification.getGradeId().equals("1")));

        verify(webSocketController, times(1)).sendNotification(eq("1"),
                contains("Your Grade for Test Assignment has been updated."));
    }
}
