package edu.depaul.cdm.se452.course;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CourseMongoRepository extends MongoRepository<CourseMongo, String> {

    Optional<CourseMongo> findByName(String name); // Query method to find by course name

    List<CourseMongo> findByInstructorId(Long instructorId); // Query method to find courses by instructor ID
}
