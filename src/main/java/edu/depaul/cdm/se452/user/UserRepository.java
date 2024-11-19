package edu.depaul.cdm.se452.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT u FROM User u JOIN u.courses c WHERE c.courseId = :courseId AND u.role = 'STUDENT'")
    List<User> findStudentsByCourseId(@Param("courseId") Long courseId);

    Optional<User> findUserByUserName(String userName);
}
