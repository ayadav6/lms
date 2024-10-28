package edu.depaul.cdm.se452.lms;


import edu.depaul.cdm.se452.discussionforum.Comment;
import edu.depaul.cdm.se452.discussionforum.CommentController;
import edu.depaul.cdm.se452.discussionforum.CommentDTO;
import edu.depaul.cdm.se452.discussionforum.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @Mock
    private Model model;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddComment() {
        CommentDTO commentForm = new CommentDTO();
        commentForm.setContent("Sample comment");

        String viewName = commentController.addComment(commentForm);

        assertEquals("redirect:/discussion/" + commentForm.getDiscussionId(), viewName);
        verify(commentService).addComment(
                commentForm.getContent(),
                commentForm.getCreatedByUserId(),
                commentForm.getDiscussionId()
        );
    }

    @Test
    void testGetCommentsByDiscussionId() {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        when(commentService.getCommentsByDiscussionId(1L)).thenReturn(comments);

        String viewName = commentController.getCommentsByDiscussionId(1L, model);

        assertEquals("comment/comments", viewName);
        verify(model).addAttribute("comments", comments);
    }
}
