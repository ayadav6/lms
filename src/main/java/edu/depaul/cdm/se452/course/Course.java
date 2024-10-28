package edu.depaul.cdm.se452.course;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

import edu.depaul.cdm.se452.user.User;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING) // Persist the enum as String in the database
    private Status status; // Use enum for status

    // Many courses can be taught by one instructor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    // No-arg constructor for JPA
    public Course() {
    }

    public Course(String name, LocalDate startDate, LocalDate endDate, Status status, User instructor) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status; // Set the status using the enum
        this.instructor = instructor;
    }

    // Enum for Course status
    public enum Status {
        ACTIVE, INACTIVE, COMPLETED;
    }

}
