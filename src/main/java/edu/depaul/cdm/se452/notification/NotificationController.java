package edu.depaul.cdm.se452.notification;

import edu.depaul.cdm.se452.assessment.Assignment;
import edu.depaul.cdm.se452.assessment.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Endpoint to send assignment notification
    @PostMapping("/assignments/{courseId}")
    public ResponseEntity<String> sendAssignmentNotification(@RequestBody Assignment assignment, @PathVariable Long courseId) {
        try {
            System.out.println("Sending assignment notification for course ID: " + courseId + ", Assignment Title: " + assignment.getTitle());
            notificationService.sendAssignmentNotification(assignment, courseId);
            return ResponseEntity.ok("Assignment notification sent successfully.");
        } catch (Exception e) {
            System.err.println("Error sending assignment notification: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to send assignment notification");
        }
    }

    // Endpoint to send grading notification
    @PostMapping("/grades")
    public ResponseEntity<String> sendGradeNotification(@RequestBody Grade grade) {
        try {
            System.out.println("Sending grade notification for Grade ID: " + grade.getId());
            notificationService.sendGradeNotification(grade);
            return ResponseEntity.ok("Grade notification sent successfully.");
        } catch (Exception e) {
            System.err.println("Error sending grade notification: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to send grade notification");
        }
    }
}
