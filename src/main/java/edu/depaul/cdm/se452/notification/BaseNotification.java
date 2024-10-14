package edu.depaul.cdm.se452.notification;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BaseNotification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    @ManyToOne
    @JoinColumn(name = "notification_id", nullable = false)
    private Notification notification; // Reference to the Notification entity
}
