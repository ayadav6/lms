package edu.depaul.cdm.se452.discussionforum;

import lombok.Data;

@Data
public class ReplyDTO {
    private Long id;
    private String content;
    private Long createdByUserId;
    private Long commentId;
}
