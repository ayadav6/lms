package edu.depaul.cdm.se452.discussionforum;


import edu.depaul.cdm.se452.course.Course;

import edu.depaul.cdm.se452.course.CourseRepository;
import edu.depaul.cdm.se452.user.User;

import edu.depaul.cdm.se452.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Discussion createDiscussion(String content, Long createdByUserId, Long courseId) {
        // Fetch the User and Course objects from their IDs
        User createdBy = userRepository.findById(createdByUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        // Create the Discussion
        Discussion discussion = new Discussion(content, createdBy, course);
        return discussionRepository.save(discussion);
    }

    public List<Discussion> getAllDiscussions() {
        return discussionRepository.findAll();
    }

    public Discussion getDiscussionById(Long id) {
        return discussionRepository.findById(id).orElse(null);
    }
}