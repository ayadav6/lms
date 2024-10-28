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

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotifificationRepository notificationRespository;

    @Autowired
    private UserRepository userRespository;

    @Autowired
    private CourseRepository courseRespository;

    @Override
    public void sendAssignmentNotification(Assignment assignment, Long courseId){
        List<User> users = userRespository.findByCourseId(courseId) // Fetch users in the course

        for (User user : users){
            Notification notification = new AssignmentNotification();
            notification.setUser(user); // Set the user being notified
            notification.setMessage("New Assignment Posted: " + assignment.getTitle());
            notification.setNotificationType("assignment");
            notification.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            notification.setIsRead(false); // Mark as unread
            

            // Set the assignment and its deadline
            notification.setAssignment(assignment);
            notification.setDeadline(assignment.getDueDate()); // Assuming dueDate is the deadline
        
            // Save the notification
            notificationRepository.save(notification);

        }
    }
}