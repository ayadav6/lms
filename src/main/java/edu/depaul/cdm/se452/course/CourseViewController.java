package edu.depaul.cdm.se452.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import java.util.List;

@Controller
public class CourseViewController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public String getAllCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "course/course-list"; // Points to course/course-list.html
    }

    // Method to show the form for adding a new course
    @GetMapping("/courses/new")
    public String showCourseForm(Model model) {
        model.addAttribute("course", new Course()); // Create a new empty course object
        return "course/course-form"; // Points to course/course-form.html
    }

    @PostMapping("/courses")
    public String saveCourse(@ModelAttribute("course") @Valid Course course, BindingResult result) {
        if (result.hasErrors()) {
            return "course/course-form"; // Return to the form if validation fails
        }

        // Save the course
        courseService.saveCourse(course);

        // Redirect to the list of courses after saving
        return "redirect:/courses";
    }

}
