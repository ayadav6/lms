package edu.depaul.cdm.se452.notification;

import edu.depaul.cdm.se452.assessment.Assignment;
import edu.depaul.cdm.se452.assessment.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationWebSocketController webSocketController;

    @Autowired
    private NotificationNoSQLRepository notificationNoSQLRepository;

    // Endpoint to send assignment notification
    @PostMapping("/assignments/{courseId}")
    public String sendAssignmentNotification(@RequestBody Assignment assignment, @PathVariable Long courseId) {
        notificationService.sendAssignmentNotification(assignment, courseId);
        return "Assignment notifications sent.";
    }

    // Endpoint to send grading notification
    @PostMapping("/grades")
    public String sendGradeNotification(@RequestBody Grade grade) {
        notificationService.sendGradeNotification(grade);
        return "Grade notification sent.";
    }

    @GetMapping("/notifications")
    public String getNotificationsForUser(@RequestParam Long userId, Model model) {
        List<NotificationDocument> notifications = notificationService.getAllNotificationsForUser(userId);
        model.addAttribute("notifications", notifications);
        return "notification/notifications";
    }

}
