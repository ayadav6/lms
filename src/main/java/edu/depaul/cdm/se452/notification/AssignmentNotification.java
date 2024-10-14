package edu.depaul.cdm.se452.notification;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "AssignmentNotification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer assignmentNotificationId;

    @ManyToOne
    @JoinColumn(name = "notification_id", nullable = false)
    private Notification notification; // Foreign key referencing base notification

    @Column(name = "assignment_id", nullable = false)
    private Integer assignmentId; // ID of the related assignment

    @Column(name = "deadline")
    private Timestamp deadline; // Deadline for the assignment (if applicable)
}
