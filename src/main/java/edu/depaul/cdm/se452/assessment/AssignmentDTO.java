package edu.depaul.cdm.se452.assessment;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class AssignmentDTO {
    private Long id;
    private String title;
    private String description;
    private String courseName;
    private Timestamp dueDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;
  
}