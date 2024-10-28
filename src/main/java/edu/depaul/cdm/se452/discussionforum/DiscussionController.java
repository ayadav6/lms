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


    @GetMapping
    public String listDiscussions(Model model) {
        List<Discussion> discussions = discussionService.getAllDiscussions();
        model.addAttribute("discussions", discussions);
        return "discussion/discussions";
    }


    @GetMapping("/{id}")
    public String viewDiscussion(@PathVariable Long id, Model model) {
        Discussion discussion = discussionService.getDiscussionById(id);
        if (discussion == null) {
            return "redirect:/discussion";
        }

        List<Comment> comments = discussion.getComments();
        model.addAttribute("discussion", discussion);
        model.addAttribute("comments", comments);
        return "discussion/discussion-details";
    }


    @GetMapping("/new")
    public String newDiscussionForm(Model model) {
        model.addAttribute("discussionForm", new DiscussionDTO());
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("title", "New Discussion");

        return "discussion/discussion-form";
    }



    @PostMapping("/create")
    public String createDiscussion(@ModelAttribute("discussionForm") DiscussionDTO discussionForm) {
        discussionService.createDiscussion(discussionForm.getContent(), discussionForm.getCreatedByUserId(), discussionForm.getCourseId());
        return "redirect:/discussion";
    }
}
