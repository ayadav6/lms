package edu.depaul.cdm.se452.discussionforum;

import edu.depaul.cdm.se452.course.Course;
import edu.depaul.cdm.se452.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DiscussionJpa extends Post {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentJpa> comments;

    // Constructors
    public DiscussionJpa() {}

    public DiscussionJpa(String content, User createdBy, Course course) {
        super(content, createdBy);
        this.course = course;
    }
}
