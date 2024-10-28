package edu.depaul.cdm.se452.assessment;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class GradeDTO {
    private Long id;
    private Long submissionId;
    private Long instructorId;
    private Double gradeValue;
    private String feedback;
    private Timestamp gradedAt;
}
