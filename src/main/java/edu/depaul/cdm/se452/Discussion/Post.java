package edu.depaul.cdm.se452.discussion;

import edu.depaul.cdm.se452.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@MappedSuperclass
@Data   // Lombok annotation to generate getters, setters, equals, hashCode, toString
@EqualsAndHashCode(callSuper = false)  // Not calling any parent class equals/hashCode
public abstract class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    private LocalDateTime createdAt;

    // Constructor sets the current timestamp for createdAt
    public Post() {
        this.createdAt = LocalDateTime.now();
    }

    // Optional constructor with fields
    public Post(String content, User createdBy) {
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
    }
}
