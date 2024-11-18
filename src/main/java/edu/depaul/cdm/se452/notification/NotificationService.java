package edu.depaul.cdm.se452.notification;

import edu.depaul.cdm.se452.assessment.Assignment;
import edu.depaul.cdm.se452.assessment.Grade;

import java.util.List;

public interface NotificationService {
    void sendAssignmentNotification(Assignment assignment, Long courseId);
    void sendGradeNotification(Grade grade);
    List<NotificationDocument> getAllNotificationsForUser(Long userId);
}