package edu.depaul.cdm.se452.notification;
import edu.depaul.cdm.se452.assessment.Assignment;
import edu.depaul.cdm.se452.assessment.Grade;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "GradingNotification")
@PrimaryKeyJoinColumn(name = "notification_id")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradingNotification extends BaseNotification {

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment; // Reference to the Assignment entity

    @ManyToOne
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade; // Reference to the Grade entity

    @Column(name = "feedback")
    private String feedback; // Feedback given by the instructor
}
