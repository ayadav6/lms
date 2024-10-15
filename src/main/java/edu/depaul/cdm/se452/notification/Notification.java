package edu.depaul.cdm.se452.notification;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.List;

import edu.depaul.cdm.se452.user.User;

@Entity
@Table(name = "Notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Reference to the User entity

    @Column(name = "message", nullable = false)
    private String message; // Notification message

    @Column(name = "notification_type", length = 50)
    private String notificationType; // Type of notification ('assignment' or 'grade')

    @Column(name = "created_date", nullable = false, updatable = false)
    private Timestamp createdDate = new Timestamp(System.currentTimeMillis()); // When the notification was created

    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false; // Has the notification been read?

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    private List<BaseNotification> baseNotifications; // Generalized list of notifications
}
