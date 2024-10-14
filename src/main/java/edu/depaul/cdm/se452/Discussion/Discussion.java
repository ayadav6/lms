package edu.depaul.cdm.se452.discussion;

import edu.depaul.cdm.se452.course.Course;
import edu.depaul.cdm.se452.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data   // Lombok annotation to generate getters, setters, etc.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Discussion extends Post {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    // Constructors
    public Discussion() {}

    public Discussion(String content, User createdBy, Course course) {
        super(content, createdBy);
        this.course = course;
    }
}

