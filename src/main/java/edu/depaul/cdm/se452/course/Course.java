package edu.depaul.cdm.se452.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

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
    @JsonBackReference
  //  @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<User> users;

    // No-arg constructor for JPA
    public Course() {
    }

    public Course(String name, LocalDate startDate, LocalDate endDate, Status status) {
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
