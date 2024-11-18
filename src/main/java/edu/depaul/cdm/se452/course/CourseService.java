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

    @Autowired
    private CourseMongoService courseMongoService; // Inject Mongo service to integrate MongoDB data

    public Course saveCourse(@NotNull @Valid Course course) {
        log.debug("Saving course: {}", course);

        // Validate dates (if applicable)
        if (course.getStartDate() != null && course.getEndDate() != null &&
                course.getEndDate().isBefore(course.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        // Save and return the course
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

    // Retrieve course by ID and merge data from SQL and MongoDB
    public CourseWithMongoData getCourseWithMongoData(Long id) {
        // Fetch the course from SQL (H2)
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            // Fetch the MongoDB course data (description and materials)
            Optional<CourseMongo> courseMongo = courseMongoService.getCourseById(id.toString());

            // Create a new object to hold both SQL and MongoDB data
            CourseWithMongoData courseWithMongoData = new CourseWithMongoData();
            courseWithMongoData.setCourse(course.get());
            courseWithMongoData.setDescription(courseMongo.map(CourseMongo::getDescription).orElse(null));
            courseWithMongoData.setMaterials(courseMongo.map(CourseMongo::getMaterials).orElse(null));

            return courseWithMongoData;
        }
        return null; // Course not found
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

    // Update an existing course
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
