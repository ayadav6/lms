package edu.depaul.cdm.se452.assessment;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class SubmissionDTO {
    private Long id;
    private Long studentId;
    private Long assignmentId;
    private String comment;
    private String fileName;
    private String fileDownloadUrl; 
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
