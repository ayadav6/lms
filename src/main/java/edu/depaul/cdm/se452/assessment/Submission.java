package edu.depaul.cdm.se452.assessment;

import lombok.Data;
import jakarta.persistence.*;
import java.sql.Timestamp;
import edu.depaul.cdm.se452.user.User;

@Data
@Entity
@Table(name = "Submission")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment; 

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student; 

    private Timestamp submittedAt = new Timestamp(System.currentTimeMillis());
    private String filePath;
    private boolean lateSubmission;
    private Double gradeValue; 
    private boolean graded = false;
    private String feedback;
}
