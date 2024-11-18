package edu.depaul.cdm.se452.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class CourseMongoService {

    @Autowired
    private CourseMongoRepository courseMongoRepository;

    // Update or set course description
    public CourseMongo updateDescription(String id, String description) {
        Optional<CourseMongo> courseOpt = courseMongoRepository.findById(id);
        if (courseOpt.isPresent()) {
            CourseMongo course = courseOpt.get();
            course.setDescription(description);
            return courseMongoRepository.save(course);
        }
        return null; // Course not found
    }

    // Add or update course materials
    public CourseMongo updateMaterials(String id, List<String> materials) {
        Optional<CourseMongo> courseOpt = courseMongoRepository.findById(id);
        if (courseOpt.isPresent()) {
            CourseMongo course = courseOpt.get();
            course.setMaterials(materials);
            return courseMongoRepository.save(course);
        }
        return null; // Course not found
    }

    // Retrieve course by ID
    public Optional<CourseMongo> getCourseById(String id) {
        return courseMongoRepository.findById(id);
    }

    // Retrieve all courses from MongoDB
    public List<CourseMongo> getAllCourses() {
        return courseMongoRepository.findAll();
    }
}
