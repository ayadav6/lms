package edu.depaul.cdm.se452.discussionforum;


import edu.depaul.cdm.se452.user.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "replies")
public class ReplyMongo {

    @Id
    private String id;

    private String content;

    @DBRef
    private User createdBy;

    @DBRef
    private CommentJpa comment;


    public ReplyMongo() {}


    public ReplyMongo(String content, User createdBy, CommentJpa comment) {
        this.content = content;
        this.createdBy = createdBy;
        this.comment = comment;
    }
}
