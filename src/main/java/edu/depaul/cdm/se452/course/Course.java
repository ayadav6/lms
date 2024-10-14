package edu.depaul.cdm.se452.course;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import edu.depaul.cdm.se452.user.User;

@Entity
@Data
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long courseId;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    // Many courses can be taught by one instructor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    // One course can have many enrollments
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enrollment> enrollments = new HashSet<>();

    // No-arg constructor for JPA
    public Course() {
    }

    public Course(String name, LocalDate startDate, LocalDate endDate, String status, User instructor) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.instructor = instructor;
    }
}
