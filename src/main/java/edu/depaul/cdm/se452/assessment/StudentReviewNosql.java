package edu.depaul.cdm.se452.assessment;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "student_reviews")
@Builder
@NoArgsConstructor 
@AllArgsConstructor 
public class StudentReviewNosql {
    @Id
    private String id; // MongoDB's unique ID
    private String assignmentId;
    private String studentId;
    private String reviewText;
    private int rating; // 1 to 5 stars
    private List<String> tags; // e.g., "challenging", "helpful"
    private long Time;
}
