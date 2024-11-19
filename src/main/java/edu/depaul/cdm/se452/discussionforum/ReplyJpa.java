package edu.depaul.cdm.se452.discussionforum;

import edu.depaul.cdm.se452.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ReplyJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private CommentJpa comment;


    public ReplyJpa() {}


    public ReplyJpa(String content, User createdBy, CommentJpa comment) {
        this.content = content;
        this.createdBy = createdBy;
        this.comment = comment;
    }
}
