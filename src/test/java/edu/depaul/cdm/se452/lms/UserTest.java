package edu.depaul.cdm.se452.lms;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.depaul.cdm.se452.course.Course;
import edu.depaul.cdm.se452.course.CourseRepository;
import edu.depaul.cdm.se452.user.User;
import edu.depaul.cdm.se452.user.UserRepository;
import edu.depaul.cdm.se452.user.UserService;

class UserTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private User updatedUserDetails;
    private Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setting up a sample user
        user = new User();
       // user.setId(1L);
        user.setUserName("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password");
        user.setRole(User.Role.STUDENT);

        // Setting up updated user details
        updatedUserDetails = new User();
        updatedUserDetails.setUserName("updatedUser");
        updatedUserDetails.setEmail("updateduser@example.com");
        updatedUserDetails.setPassword("newpassword");
        updatedUserDetails.setRole(User.Role.INSTRUCTOR);

        // Setting up a sample course
        course = new Course();
        course.setCourseId(1L);
        course.setName("Test Course");
        course.setStatus(Course.Status.ACTIVE);
    }

    @Test
    void saveUser_ShouldReturnSavedUser() {
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertEquals(user.getUserName(), savedUser.getUserName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<User> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertEquals(user.getUserName(), users.get(0).getUserName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById_ShouldReturnUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals(user.getUserName(), foundUser.get().getUserName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void deleteUser_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.deleteUser(1L);
        });

        assertEquals("User not found with ID: 1", exception.getMessage());
    }



    @Test
    void updateUser_ShouldThrowException_WhenUserNotFound() {
        updatedUserDetails.setId(1L); // Set the correct ID

        when(userRepository.findById(1L)).thenReturn(Optional.empty());


        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.updateUser(updatedUserDetails);
        });

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void login_ShouldReturnTrue_WhenCredentialsAreCorrect() {
        when(userRepository.findUserByUserName("testuser")).thenReturn(Optional.of(user));

        boolean loginSuccess = userService.login("testuser", "password");

        assertTrue(loginSuccess);
        verify(userRepository, times(1)).findUserByUserName("testuser");
    }

    @Test
    void login_ShouldReturnFalse_WhenCredentialsAreIncorrect() {
        when(userRepository.findUserByUserName("testuser")).thenReturn(Optional.of(user));

        boolean loginSuccess = userService.login("testuser", "wrongpassword");

        assertFalse(loginSuccess);
        verify(userRepository, times(1)).findUserByUserName("testuser");
    }
}
