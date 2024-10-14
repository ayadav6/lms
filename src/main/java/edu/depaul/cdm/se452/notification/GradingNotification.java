package edu.depaul.cdm.se452.notification;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "GradingNotification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradingNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gradingNotificationId;

    @ManyToOne
    @JoinColumn(name = "notification_id", nullable = false)
    private Notification notification; // Foreign key referencing base notification

    @Column(name = "assignment_id", nullable = false)
    private Integer assignmentId; // ID of the graded assignment

    @Column(name = "grade", precision = 3, scale = 0) // Grade received for the assignment
    @Min(1) // Minimum score is 1
    @Max(100) // Maximum score is 100
    private Integer grade; // Change to Integer for whole number scores

    @Column(name = "feedback")
    private String feedback; // Feedback given by the instructor
}
