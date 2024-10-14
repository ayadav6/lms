package edu.depaul.cdm.se452.notification;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AssignmentNotification")
@PrimaryKeyJoinColumn(name = "notification_id")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentNotification extends BaseNotification {

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment; // Reference to the Assignment entity

    @Column(name = "deadline")
    private Timestamp deadline; // Deadline for the assignment (if applicable)
}
