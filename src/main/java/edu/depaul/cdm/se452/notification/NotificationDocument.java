package edu.depaul.cdm.se452.notification;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "notifications")
@Data  
@NoArgsConstructor 
@AllArgsConstructor  
public class NotificationDocument {
    @Id
    private String id;
    private String notificationType;  
    private String userId; 
    private String message;  
    private boolean isRead; 
    private long createdDate;  

    private String assignmentId;
    private String gradeId;
}
