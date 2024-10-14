package edu.depaul.cdm.se452.discussion;

import edu.depaul.cdm.se452.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@EqualsAndHashCode(callSuper = true)  // Call equals and hashCode from superclass Post
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Reply extends Post {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    // Constructors
    public Reply() {}

    public Reply(String content, User createdBy, Comment comment) {
        super(content, createdBy);
        this.comment = comment;
    }
}
