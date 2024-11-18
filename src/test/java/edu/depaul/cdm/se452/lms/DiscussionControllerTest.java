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

    private boolean useMongo;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        // Use reflection to set the private 'useMongo' field
        java.lang.reflect.Field useMongoField = DiscussionController.class.getDeclaredField("useMongo");
        useMongoField.setAccessible(true);
        useMongoField.set(discussionController, false); // Default to JPA
    }


    @Test
    void testListDiscussionsJpa() {
        useMongo = false; // Test for JPA
        List<DiscussionJpa> discussions = new ArrayList<>();
        discussions.add(new DiscussionJpa());

        when(discussionService.getAllDiscussionsJpa()).thenReturn(discussions);

        String viewName = discussionController.listDiscussions(model);
        assertEquals("discussion/discussions", viewName);
        verify(discussionService, times(1)).getAllDiscussionsJpa();
        verify(model, times(1)).addAttribute("discussions", discussions);
    }

    @Test
    void testListDiscussionsMongo() {
        useMongo = true; // Test for MongoDB
        List<DiscussionMongo> discussions = new ArrayList<>();
        discussions.add(new DiscussionMongo("Sample Content", 1L, 1L));

        when(discussionService.getAllDiscussionsMongo()).thenReturn(discussions);

        String viewName = discussionController.listDiscussions(model);
        assertEquals("discussion/discussions", viewName);
        verify(discussionService, times(1)).getAllDiscussionsMongo();
        verify(model, times(1)).addAttribute("discussions", discussions);
    }

    @Test
    void testViewDiscussionJpa() {
        useMongo = false; // Test for JPA
        Long discussionId = 1L;
        DiscussionJpa discussion = new DiscussionJpa();
        List<CommentJpa> comments = new ArrayList<>();
        discussion.setId(discussionId);
        discussion.setComments(comments);

        when(discussionService.getDiscussionJpaById(discussionId)).thenReturn(discussion);

        String viewName = discussionController.viewDiscussion(String.valueOf(discussionId), model);
        assertEquals("discussion/discussion-details", viewName);
        verify(discussionService, times(1)).getDiscussionJpaById(discussionId);
        verify(model, times(1)).addAttribute("discussion", discussion);
        verify(model, times(1)).addAttribute("comments", comments);
    }

    @Test
    void testViewDiscussionMongo() {
        useMongo = true; // Test for MongoDB
        String discussionId = "1";
        DiscussionMongo discussion = new DiscussionMongo("Sample Content", 1L, 1L);

        when(discussionService.getDiscussionMongoById(discussionId)).thenReturn(discussion);

        String viewName = discussionController.viewDiscussion(discussionId, model);
        assertEquals("discussion/discussion-details", viewName);
        verify(discussionService, times(1)).getDiscussionMongoById(discussionId);
        verify(model, times(1)).addAttribute("discussion", discussion);
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
    void testCreateDiscussionJpa() {
        useMongo = false; // Test for JPA
        DiscussionDTO discussionForm = new DiscussionDTO();
        discussionForm.setContent("Test Discussion Content");
        discussionForm.setCreatedByUserId(1L);
        discussionForm.setCourseId(1L);

        String viewName = discussionController.createDiscussion(discussionForm);
        assertEquals("redirect:/discussions", viewName);
        verify(discussionService, times(1))
                .createDiscussionJpa(discussionForm.getContent(), discussionForm.getCreatedByUserId(), discussionForm.getCourseId());
    }

    @Test
    void testCreateDiscussionMongo() {
        useMongo = true; // Test for MongoDB
        DiscussionDTO discussionForm = new DiscussionDTO();
        discussionForm.setContent("Test Discussion Content");
        discussionForm.setCreatedByUserId(1L);
        discussionForm.setCourseId(1L);

        String viewName = discussionController.createDiscussion(discussionForm);
        assertEquals("redirect:/discussions", viewName);
        verify(discussionService, times(1))
                .createDiscussionMongo(discussionForm.getContent(), discussionForm.getCreatedByUserId(), discussionForm.getCourseId());
    }
}
