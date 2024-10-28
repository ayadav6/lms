package edu.depaul.cdm.se452.notification;

import edu.depaul.cdm.se452.assessment.Assignment;
import edu.depaul.cdm.se452.assessment.Grade;
import edu.depaul.cdm.se452.course.Course;
import edu.depaul.cdm.se452.user.User;


public interface NotificationService {
    void sendAssignmentNotification(Assignment assignment, Long courseId);
    void sendGradeNotification(Grade grade, Long studentId);
}