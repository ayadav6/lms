package edu.depaul.cdm.se452.assessment;

import lombok.Data;
import jakarta.persistence.*;
import java.sql.Timestamp;
import edu.depaul.cdm.se452.user.User;

@Data
@Entity
@Table(name = "Grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission; 

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor; 

    private Double gradeValue;
    private String feedback;
    private Timestamp gradedAt = new Timestamp(System.currentTimeMillis());
}
