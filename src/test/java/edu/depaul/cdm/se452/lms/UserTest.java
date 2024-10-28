package edu.depaul.cdm.se452.lms;

import edu.depaul.cdm.se452.course.Course;
import edu.depaul.cdm.se452.user.User;
import edu.depaul.cdm.se452.user.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        // Initialize the test user object before each test
        testUser = new User();
        testUser.setUserName("john_doe");
        testUser.setPassword("password123");
        testUser.setRole(User.Role.STUDENT);
        testUser.setEmail("john.doe@example.com");
        testUser.setCreatedAt(LocalDateTime.now());

        // Save the user to the in-memory database
        userRepository.save(testUser);
    }

    @Test
    public void testCreateUser() {
        // Assert that the user has been saved and has an ID
        User savedUser = userRepository.findById(testUser.getId()).orElse(null);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUserName()).isEqualTo("john_doe");
    }

    @Test
    public void testUserCourseAssociation() {
        // Create a Course
        Course course = new Course();
        course.setName("Math 101");
        course.setStartDate(LocalDateTime.now().toLocalDate());
        course.setEndDate(LocalDateTime.now().plusMonths(3).toLocalDate());
        course.setInstructor(testUser); // Linking to the user as instructor

        // Add the course to the user's course list
        testUser.setCourseList(Collections.singletonList(course));

        // Save the user with the associated course
        userRepository.save(testUser);

        // Fetch the user and validate course association
        User fetchedUser = userRepository.findById(testUser.getId()).orElse(null);
        List<Course> courses = fetchedUser.getCourseList();
        assertThat(courses).isNotEmpty();
        assertThat(courses.get(0).getName()).isEqualTo("Math 101");
    }

    @Test
    public void testDeleteUser() {
        // Delete the user from the repository
        userRepository.delete(testUser);

        // Ensure the user no longer exists
        assertThat(userRepository.findById(testUser.getId()).isPresent()).isFalse();
    }

}
