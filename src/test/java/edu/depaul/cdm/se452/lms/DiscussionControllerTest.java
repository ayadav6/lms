package edu.depaul.cdm.se452.lms;


import edu.depaul.cdm.se452.course.CourseService;
import edu.depaul.cdm.se452.discussionforum.*;
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

class DiscussionControllerTest {

    @Mock
    private DiscussionService discussionService;

    @Mock
    private CourseService courseService;

    @Mock
    private Model model;

    @InjectMocks
    private DiscussionController discussionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListDiscussions() {
        List<Discussion> discussions = new ArrayList<>();
        discussions.add(new Discussion());

        when(discussionService.getAllDiscussions()).thenReturn(discussions);

        String viewName = discussionController.listDiscussions(model);
        assertEquals("discussion/discussions", viewName);
        verify(discussionService, times(1)).getAllDiscussions();
        verify(model, times(1)).addAttribute("discussions", discussions);
    }

    @Test
    void testViewDiscussion() {
        Long discussionId = 1L;
        Discussion discussion = new Discussion();
        List<Comment> comments = new ArrayList<>();
        discussion.setId(discussionId);
        discussion.setComments(comments);

        when(discussionService.getDiscussionById(discussionId)).thenReturn(discussion);

        String viewName = discussionController.viewDiscussion(discussionId, model);
        assertEquals("discussion/discussion-details", viewName);
        verify(discussionService, times(1)).getDiscussionById(discussionId);
        verify(model, times(1)).addAttribute("discussion", discussion);
        verify(model, times(1)).addAttribute("comments", comments);
    }

    @Test
    void testNewDiscussionForm() {
        when(courseService.getAllCourses()).thenReturn(new ArrayList<>());

        String viewName = discussionController.newDiscussionForm(model);
        assertEquals("discussion/discussion-form", viewName);
        verify(courseService, times(1)).getAllCourses();
        verify(model, times(1)).addAttribute(eq("discussionForm"), any(DiscussionDTO.class));
        verify(model, times(1)).addAttribute("courses", new ArrayList<>());
    }

    @Test
    void testCreateDiscussion() {
        DiscussionDTO discussionForm = new DiscussionDTO();
        discussionForm.setContent("Test Discussion Content");
        discussionForm.setCreatedByUserId(1L);
        discussionForm.setCourseId(1L);

        String viewName = discussionController.createDiscussion(discussionForm);
        assertEquals("redirect:/discussion", viewName);
        verify(discussionService, times(1))
                .createDiscussion(discussionForm.getContent(), discussionForm.getCreatedByUserId(), discussionForm.getCourseId());
    }
}
