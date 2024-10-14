package edu.depaul.cdm.se452.notification;
import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Notification")
@Data // Generates getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Generates a no-args constructor
@AllArgsConstructor // Generates an all-args constructor
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer notificationId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "notification_type", length = 50)
    private String notificationType;

    @Column(name = "created_date")
    private Timestamp createdDate = new Timestamp(System.currentTimeMillis());

    @Column(name = "is_read")
    private Boolean isRead = false;
}