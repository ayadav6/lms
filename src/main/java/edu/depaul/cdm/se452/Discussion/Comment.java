package edu.depaul.cdm.se452.discussion;

import edu.depaul.cdm.se452.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@EqualsAndHashCode(callSuper = true)  // Call equals and hashCode from superclass Post
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Comment extends Post {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discussion_id", nullable = false)
    private Discussion discussion;

    // Constructors
    public Comment() {}

    public Comment(String content, User createdBy, Discussion discussion) {
        super(content, createdBy);
        this.discussion = discussion;
    }
}

