package edu.depaul.cdm.se452.assessment;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import edu.depaul.cdm.se452.user.User;

@Data
@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;

    
    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    private Double gradeValue;
    private String feedback;
    private Timestamp gradedAt = new Timestamp(System.currentTimeMillis());
}
