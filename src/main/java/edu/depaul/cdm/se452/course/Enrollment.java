package edu.depaul.cdm.se452.course;

import jakarta.persistence.*;
import java.time.LocalDate;

import edu.depaul.cdm.se452.user.User;

@Entity
@Table(name = "Enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @Column(nullable = false)
    private LocalDate enrollmentDate;

    // Many enrollments refer to one course
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    // Many enrollments refer to one student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    // Constructors, Getters, and Setters
    public Enrollment() {
    }

    public Enrollment(LocalDate enrollmentDate, String grade, Course course, User student) {
        this.enrollmentDate = enrollmentDate;
        this.course = course;
        this.student = student;
    }

}
