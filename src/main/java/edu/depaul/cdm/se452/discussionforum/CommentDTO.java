package edu.depaul.cdm.se452.discussionforum;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String content;
    private Long createdByUserId;
    private Long discussionId;
}
