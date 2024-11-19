package edu.depaul.cdm.se452.lms;

import edu.depaul.cdm.se452.discussionforum.CommentController;
import edu.depaul.cdm.se452.discussionforum.CommentDTO;
import edu.depaul.cdm.se452.discussionforum.CommentJpa;
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
        commentForm.setCreatedByUserId(1L);
        commentForm.setDiscussionId(1L);

        boolean useMongo = false; // Adjust for MongoDB or H2

        String viewName = commentController.addComment(commentForm);

        verify(commentService).addComment(
                commentForm.getContent(),
                commentForm.getCreatedByUserId(),
                commentForm.getDiscussionId(),
                useMongo
        );

        assertEquals("redirect:/discussion/" + commentForm.getDiscussionId(), viewName);
    }

    @Test
    void testGetCommentsByDiscussionId() {
        Long discussionId = 1L;
        boolean useMongo = false; // UseMongo flag

        List<CommentJpa> comments = new ArrayList<>();
        CommentJpa comment = new CommentJpa();
        comment.setContent("Test Comment");
        comments.add(comment);

        // Use doReturn to mock the service method
        doReturn(comments)
                .when(commentService)
                .getCommentsByDiscussionId(eq(discussionId), eq(useMongo));

        // Call the controller method
        String viewName = commentController.getCommentsByDiscussionId(discussionId, model);

        // Assertions
        assertEquals("comment/comments", viewName);

        // Verify that the mocked service method was called
        verify(commentService, times(1)).getCommentsByDiscussionId(eq(discussionId), eq(useMongo));
        verify(model, times(1)).addAttribute("comments", comments);
    }


}
