package edu.depaul.cdm.se452.assessment;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import edu.depaul.cdm.se452.course.*;

@Entity
@Data
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Timestamp dueDate;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course; 

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    private String fileName;

    @Lob  
    private byte[] fileData;
    private String fileType;

}
