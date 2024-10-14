package edu.depaul.cdm.se452.notification;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer notificationId;

    @Column(name = "user_id", nullable = false)
    private Integer userId; // ID of the user receiving the notification

    @Column(name = "message", nullable = false)
    private String message; // Notification message

    @Column(name = "notification_type", length = 50)
    private String notificationType; // Type of notification ('assignment' or 'grade')

    @Column(name = "created_date", nullable = false, updatable = false)
    private Timestamp createdDate = new Timestamp(System.currentTimeMillis()); // When the notification was created

    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false; // Has the notification been read?

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    private List<AssignmentNotification> assignmentNotifications;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    private List<GradingNotification> gradingNotifications;
}
