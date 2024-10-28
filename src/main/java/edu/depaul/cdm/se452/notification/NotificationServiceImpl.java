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
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private AssignmentNotificationRepository assignmentNotificationRepository;

    @Autowired
    private GradingNotificationRepository gradingNotificationRepository;


    @Autowired
    private UserRepository userRepository;

    @Override
    public void sendAssignmentNotification(Assignment assignment, Long courseId){
        // List<User> users = userRepository.getStudentByCourseId(courseId); // Fetch users in the course
        List<User> users = new ArrayList<>(); // No users will be notified

        if (users == null || users.isEmpty()) {
            System.out.println("No students found for course ID: " + courseId);
            return;
        }


        for (User user : users){
            AssignmentNotification notification = new AssignmentNotification();
            notification.setUser(user); // Set the user being notified
            notification.setMessage("New Assignment Posted: " + assignment.getTitle());
            notification.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            notification.setIsRead(false); // Mark as unread
            

            // Set the assignment and its deadline
            notification.setAssignment(assignment);
            notification.setDeadline(assignment.getDueDate()); // Assuming dueDate is the deadline
        
            // Save the notification
            assignmentNotificationRepository.save(notification);

        }
    }

    @Override
    public void sendGradeNotification(Grade grade) {
        // Assuming each Grade object has a Submission that can get the student
        User student = grade.getSubmission().getStudent(); // Get the student user from submission

        GradingNotification gradingNotification = new GradingNotification();
        gradingNotification.setUser(student); // Set the user being notified
        gradingNotification.setMessage("Grade Posted for Assignment: " + grade.getSubmission().getAssignment().getTitle());
        gradingNotification.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        gradingNotification.setIsRead(false); // Mark as unread

        // Set the grade and feedback
        gradingNotification.setGrade(grade);
        gradingNotification.setFeedback(grade.getFeedback()); // Assuming feedback is provided by the instructor

        // Save the grading notification
        gradingNotificationRepository.save(gradingNotification);
    }
}