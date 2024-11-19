package edu.depaul.cdm.se452.user;

import edu.depaul.cdm.se452.course.Course;
import edu.depaul.cdm.se452.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    // Get all students by course ID
    public List<User> getStudentsByCourseId(Long courseId) {
        return userRepository.findStudentsByCourseId(courseId);
    }



    public boolean login(String username, String password) {
        Optional<User> userOptional = userRepository.findUserByUserName(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return password.equals(user.getPassword());
        }
        return false;
    }

    public void updateUser(UserDTO userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update basic user details
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        // Map course IDs to course entities
        if(userDto.getCourseIds() != null) {
            List<Course> selectedCourses = courseRepository.findAllById(userDto.getCourseIds());
            user.setCourses(selectedCourses);
        }else{
            user.setCourses(null);
        }
        userRepository.save(user);
    }

    public void updateUser(User user) {
        // Find existing user by ID
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Update basic user details
        existingUser.setUserName(user.getUserName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());

        // Update course associations (if any)
        if(user.getCourses() != null) {
            existingUser.setCourses(user.getCourses());
        } else {
            existingUser.setCourses(null);
        }

        // Save updated user
        userRepository.save(existingUser);
    }

    public  Optional<User> findByUsername(String name) {
        Optional<User> user= userRepository.findUserByUserName(name);
       return user;
    }
}
