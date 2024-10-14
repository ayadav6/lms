package edu.depaul.cdm.se452.assessment;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import edu.depaul.cdm.se452.course.Course;

@Data
@Entity
@Table(name = "Assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course; 

    private Timestamp dueDate;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}