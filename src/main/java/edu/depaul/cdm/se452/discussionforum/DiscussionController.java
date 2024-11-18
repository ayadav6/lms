package edu.depaul.cdm.se452.discussionforum;

import edu.depaul.cdm.se452.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/discussions")
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    @Autowired
    private CourseService courseService;

    private boolean useMongo = false;

    @GetMapping
    public String listDiscussions(Model model) {
        if (useMongo) {
            List<DiscussionMongo> discussions = discussionService.getAllDiscussionsMongo();
            model.addAttribute("discussions", discussions);
        } else {
            List<DiscussionJpa> discussions = discussionService.getAllDiscussionsJpa();
            model.addAttribute("discussions", discussions);
        }
        return "discussion/discussions";
    }

    @GetMapping("/{id}")
    public String viewDiscussion(@PathVariable String id, Model model) {
        if (useMongo) {
            DiscussionMongo discussion = discussionService.getDiscussionMongoById(id);
            model.addAttribute("discussion", discussion);
        } else {
            DiscussionJpa discussion = discussionService.getDiscussionJpaById(Long.parseLong(id));
            model.addAttribute("discussion", discussion);
            model.addAttribute("comments", discussion.getComments());
        }
        return "discussion/discussion-details";
    }

    @GetMapping("/new")
    public String newDiscussionForm(Model model) {
        model.addAttribute("discussionForm", new DiscussionDTO());
        model.addAttribute("courses", courseService.getAllCourses());
        return "discussion/discussion-form";
    }

    @PostMapping("/create")
    public String createDiscussion(@ModelAttribute("discussionForm") DiscussionDTO discussionForm) {
        if (useMongo) {
            discussionService.createDiscussionMongo(
                    discussionForm.getContent(),
                    discussionForm.getCreatedByUserId(),
                    discussionForm.getCourseId()
            );
        } else {
            discussionService.createDiscussionJpa(
                    discussionForm.getContent(),
                    discussionForm.getCreatedByUserId(),
                    discussionForm.getCourseId()
            );
        }
        return "redirect:/discussions";
    }
}
