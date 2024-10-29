package edu.depaul.cdm.se452.notification;

import java.sql.Timestamp;
import edu.depaul.cdm.se452.assessment.Assignment;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("assignment")
public class AssignmentNotification extends Notification {

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @Column(name = "deadline")
    private Timestamp deadline;
}
