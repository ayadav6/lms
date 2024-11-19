package edu.depaul.cdm.se452.discussionforum;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;

    @NotNull(message = "Content is required")
    @Size(min = 5, max = 500, message = "Content must be between 5 and 500 characters")
    private String content;

    private Long createdByUserId;
    private Long discussionId;
}

