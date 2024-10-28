package edu.depaul.cdm.se452.lms;


import edu.depaul.cdm.se452.discussionforum.Reply;
import edu.depaul.cdm.se452.discussionforum.ReplyController;
import edu.depaul.cdm.se452.discussionforum.ReplyDTO;
import edu.depaul.cdm.se452.discussionforum.ReplyService;
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

class ReplyControllerTest {

    @Mock
    private ReplyService replyService;

    @Mock
    private Model model;

    @InjectMocks
    private ReplyController replyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddReply() {
        ReplyDTO replyForm = new ReplyDTO();
        replyForm.setContent("Sample reply");

        String viewName = replyController.addReply(replyForm);

        assertEquals("redirect:/comment/" + replyForm.getCommentId(), viewName);
        verify(replyService).addReply(
                replyForm.getContent(),
                replyForm.getCreatedByUserId(),
                replyForm.getCommentId()
        );
    }

    @Test
    void testGetRepliesByCommentId() {
        List<Reply> replies = new ArrayList<>();
        replies.add(new Reply());
        when(replyService.getRepliesByCommentId(1L)).thenReturn(replies);

        String viewName = replyController.getRepliesByCommentId(1L, model);

        assertEquals("reply/replies", viewName);
        verify(model).addAttribute("replies", replies);
    }
}
