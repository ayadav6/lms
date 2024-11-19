package edu.depaul.cdm.se452.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // Find courses by instructor ID
   List<Course> findByInstructorId(Long instructorId);

    // Find courses by name
    List<Course> findByNameContaining(String name);

}
