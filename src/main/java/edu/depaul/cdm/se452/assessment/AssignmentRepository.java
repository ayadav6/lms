package edu.depaul.cdm.se452.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    // Find all assignments by course ID
    List<Assignment> findByCourse_CourseId(Long courseId);

    // Find assignments by course name using a custom query
    @Query("SELECT a FROM Assignment a WHERE a.course.name = :courseName")
    List<Assignment> findAssignmentsByCourseName(String courseName);


    // Find assignments by title containing a specific keyword (case-insensitive)
    List<Assignment> findByTitleContainingIgnoreCase(String keyword);
}