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
    private DiscussionJpaRepository discussionJpaRepository;

    @Autowired
    private DiscussionMongoRepository discussionMongoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public DiscussionJpa createDiscussionJpa(String content, Long createdByUserId, Long courseId) {
        User createdBy = userRepository.findById(createdByUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        DiscussionJpa discussion = new DiscussionJpa(content, createdBy, course);
        return discussionJpaRepository.save(discussion);
    }

    public DiscussionMongo createDiscussionMongo(String content, Long createdByUserId, Long courseId) {
        DiscussionMongo discussion = new DiscussionMongo(content, createdByUserId, courseId);
        return discussionMongoRepository.save(discussion);
    }

    public List<DiscussionJpa> getAllDiscussionsJpa() {
        return discussionJpaRepository.findAll();
    }

    public List<DiscussionMongo> getAllDiscussionsMongo() {
        return discussionMongoRepository.findAll();
    }

    public DiscussionJpa getDiscussionJpaById(Long id) {
        return discussionJpaRepository.findById(id).orElse(null);
    }

    public DiscussionMongo getDiscussionMongoById(String id) {
        return discussionMongoRepository.findById(id).orElse(null);
    }
}
