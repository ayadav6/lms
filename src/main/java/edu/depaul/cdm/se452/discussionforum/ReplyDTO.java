package edu.depaul.cdm.se452.discussionforum;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReplyDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Long createdByUserId;
    private Long commentId;
}
