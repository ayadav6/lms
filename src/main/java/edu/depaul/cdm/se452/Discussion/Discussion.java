package edu.depaul.cdm.se452.discussion;

import edu.depaul.cdm.se452.course.Course;
import jakarta.persistence.*;
import lombok.Data;
// Only keep the necessary imports


@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Discussion extends Post {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}

