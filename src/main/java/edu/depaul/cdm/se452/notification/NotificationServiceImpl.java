package edu.depaul.cdm.se452.notification;

import edu.depaul.cdm.se452.assessment.Assignment;
import edu.depaul.cdm.se452.assessment.Grade;
import edu.depaul.cdm.se452.course.Course;
import edu.depaul.cdm.se452.course.CourseRepository;
import edu.depaul.cdm.se452.user.User;
import edu.depaul.cdm.se452.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;


@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationNoSQLRepository notificationNoSQLRepository;

    @Autowired
    private NotificationWebSocketController webSocketController;

    @Override
    public void sendAssignmentNotification(Assignment assignment, Long courseId) {
        List<User> users = userRepository.findStudentsByCourseId(courseId);

        for (User user : users) {
            // Save notification in MongoDB
            NotificationDocument notification = new NotificationDocument();
            notification.setNotificationType("assignment");
            notification.setUserId(String.valueOf(user.getId())); // Use String.valueOf
            notification.setMessage("New Assignment Posted: " + assignment.getTitle());
            notification.setAssignmentId(String.valueOf(assignment.getId())); // Use String.valueOf
            notification.setCreatedDate(System.currentTimeMillis());
            notification.setRead(false);
            notificationNoSQLRepository.save(notification);

            // Send WebSocket notification
            webSocketController.sendNotification(String.valueOf(user.getId()),
                    "New Assignment Posted: " + assignment.getTitle());
        }



    }

    @Override
    public void sendGradeNotification(Grade grade) {
        User student = grade.getSubmission().getStudent();

        // Save notification in MongoDB
        NotificationDocument notification = new NotificationDocument();
        notification.setNotificationType("grading");
        notification.setUserId(String.valueOf(student.getId())); // Use String.valueOf
        notification.setMessage("Grade Updated for: " + grade.getSubmission().getAssignment().getTitle());
        notification.setGradeId(String.valueOf(grade.getId())); // Use String.valueOf
        notification.setCreatedDate(System.currentTimeMillis());
        notification.setRead(false);
        notificationNoSQLRepository.save(notification);

        // Send WebSocket notification
        webSocketController.sendNotification(String.valueOf(student.getId()),
                "Your Grade for " + grade.getSubmission().getAssignment().getTitle() + " has been updated.");
  
    }
    
    @Override
    public List<NotificationDocument> getAllNotificationsForUser(Long userId) {
        return notificationNoSQLRepository.findByUserId(String.valueOf(userId));
    }


}