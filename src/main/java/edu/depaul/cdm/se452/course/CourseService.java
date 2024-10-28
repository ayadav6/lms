package edu.depaul.cdm.se452.course;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Save or update a course
    public Course saveCourse(@NotNull @Valid Course course) {
        log.debug("Saving course: {}", course);
        return courseRepository.save(course);
    }

    // Retrieve all courses
    public List<Course> getAllCourses() {
        log.debug("Fetching all courses from the repository");
        return courseRepository.findAll();
    }

    // Retrieve a course by ID
    public Optional<Course> getCourseById(Long id) {
        log.debug("Fetching course by ID: {}", id);
        return courseRepository.findById(id);
    }

    // Delete a course
    public void deleteCourse(Long id) {
        log.debug("Deleting course by ID: {}", id);
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            courseRepository.deleteById(id);
            log.info("Course with ID: {} deleted successfully", id);
        } else {
            log.warn("Course not found with ID: {}", id);
            throw new IllegalArgumentException("Course not found with ID: " + id);
        }
    }

    public Course updateCourse(Long id, Course updatedCourseDetails) {
        // Retrieve the existing course from the database
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + id));

        // Update course details with the provided values
        if (updatedCourseDetails.getName() != null) {
            course.setName(updatedCourseDetails.getName());
        }
        if (updatedCourseDetails.getStartDate() != null) {
            course.setStartDate(updatedCourseDetails.getStartDate());
        }
        if (updatedCourseDetails.getEndDate() != null) {
            course.setEndDate(updatedCourseDetails.getEndDate());
        }
        if (updatedCourseDetails.getStatus() != null) {
            course.setStatus(updatedCourseDetails.getStatus());
        }

        log.debug("Updating course with ID: {}", id);

        // Save the updated course back to the repository
        return courseRepository.save(course);
    }

    // Retrieve courses by instructor ID
    public List<Course> getCoursesByInstructorId(Long instructorId) {
        log.debug("Fetching courses for instructor ID: {}", instructorId);
        return courseRepository.findByInstructorId(instructorId);
    }

    // Retrieve courses by name
    public List<Course> getCoursesByName(String name) {
        log.debug("Fetching courses with name containing: {}", name);
        return courseRepository.findByNameContaining(name);
    }
}
