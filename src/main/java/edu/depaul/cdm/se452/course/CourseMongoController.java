package edu.depaul.cdm.se452.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mongo/courses")
public class CourseMongoController {

    @Autowired
    private CourseMongoService courseMongoService;

    // Endpoint to update course description
    @PutMapping("/{id}/description")
    public ResponseEntity<CourseMongo> updateCourseDescription(
            @PathVariable String id, @RequestBody String description) {
        CourseMongo updatedCourse = courseMongoService.updateDescription(id, description);
        return updatedCourse != null
                ? ResponseEntity.ok(updatedCourse)
                : ResponseEntity.notFound().build();
    }

    // Endpoint to add/update course materials
    @PutMapping("/{id}/materials")
    public ResponseEntity<CourseMongo> updateCourseMaterials(
            @PathVariable String id, @RequestBody List<String> materials) {
        CourseMongo updatedCourse = courseMongoService.updateMaterials(id, materials);
        return updatedCourse != null
                ? ResponseEntity.ok(updatedCourse)
                : ResponseEntity.notFound().build();
    }

    // Endpoint to retrieve course by ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseMongo> getCourseById(@PathVariable String id) {
        Optional<CourseMongo> courseOpt = courseMongoService.getCourseById(id);
        return courseOpt.isPresent()
                ? ResponseEntity.ok(courseOpt.get())
                : ResponseEntity.notFound().build();
    }
}
