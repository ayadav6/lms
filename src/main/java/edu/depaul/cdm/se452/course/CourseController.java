package edu.depaul.cdm.se452.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/courses")
@Slf4j
@Validated
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Retrieve all courses
    @GetMapping
    @Operation(summary = "Retrieve all courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        log.info("Retrieving all courses");
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // Create a new course
    @PostMapping
    @Operation(summary = "Create a new course")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Course created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid course data")
    })
    public ResponseEntity<?> createCourse(@Valid @RequestBody Course course) {
        log.info("Creating new course with name: {}", course.getName());
        Course savedCourse = courseService.saveCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    // Retrieve a course by ID
    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a course by ID")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        log.info("Fetching course with ID: {}", id);
        return courseService.getCourseById(id)
                .map(course -> ResponseEntity.ok(course))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Delete a course by ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a course by ID")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        log.info("Attempting to delete course with ID: {}", id);
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok("Course deleted successfully");
        } catch (IllegalArgumentException e) {
            log.error("Error deleting course: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Update a course by ID
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long id,
            @RequestBody Course updatedCourseDetails) {
        Course updatedCourse = courseService.updateCourse(id, updatedCourseDetails);
        return ResponseEntity.ok(updatedCourse);
    }

    // Retrieve courses by instructor ID
    @GetMapping("/instructor/{instructorId}")
    @Operation(summary = "Retrieve courses by instructor ID")
    public ResponseEntity<List<Course>> getCoursesByInstructorId(@PathVariable Long instructorId) {
        log.info("Retrieving courses for instructor ID: {}", instructorId);
        List<Course> courses = courseService.getCoursesByInstructorId(instructorId);
        return ResponseEntity.ok(courses);
    }

    // Retrieve courses by name
    @GetMapping("/name/{name}")
    @Operation(summary = "Retrieve courses by name")
    public ResponseEntity<List<Course>> getCoursesByName(@PathVariable String name) {
        log.info("Retrieving courses with name containing: {}", name);
        List<Course> courses = courseService.getCoursesByName(name);
        return ResponseEntity.ok(courses);
    }
}
