package edu.depaul.cdm.se452.notification;

import edu.depaul.cdm.se452.assessment.Assignment;
import edu.depaul.cdm.se452.assessment.Grade;


public interface NotificationService {
    void sendNewAssignmentNotification(Assignment assignment);
    void sendAssignmentDeadlineNotification(Assignment assignment);
    void sendGradePostedNotification(Grade grade);
}