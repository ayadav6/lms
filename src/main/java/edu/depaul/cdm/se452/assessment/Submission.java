package edu.depaul.cdm.se452.assessment;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import edu.depaul.cdm.se452.user.User;

@Data
@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student; 

    @OneToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment; 
    
    private String comment;
    private String fileName;
    private boolean graded = false;
    private Double gradeValue; 
    
    private Timestamp SubmittedAt;
    private Timestamp updatedAt;

    @Lob  
    private byte[] fileData;

}
