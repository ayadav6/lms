package edu.depaul.cdm.se452.discussionforum;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "discussions")
public class DiscussionMongo {

    @Id
    private String id;
    private String content;
    private Long createdByUserId;
    private Long courseId;

    // Constructors
    public DiscussionMongo() {}

    public DiscussionMongo(String content, Long createdByUserId, Long courseId) {
        this.content = content;
        this.createdByUserId = createdByUserId;
        this.courseId = courseId;
    }
}
