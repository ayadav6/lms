package edu.depaul.cdm.se452.user;

import edu.depaul.cdm.se452.course.Course;
import edu.depaul.cdm.se452.course.CourseRepository;
import edu.depaul.cdm.se452.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/")
public class UserController {

        @Autowired
        private UserService userService;

        @Autowired
        private CourseRepository courseRepository;

        @Autowired
        CourseService courseService;

//        @GetMapping
//        public ResponseEntity<List<User>> getAllUsers() {
//            List<User> users = userService.getAllUsers();
//            return new ResponseEntity<>(users, HttpStatus.OK);
//        }
//
        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            return userService.getUserById(id)
                    .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }



    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<User>> getStudentsByCourseId(@PathVariable Long courseId) {

        return courseService.getCourseById(courseId)
                .map(course -> {
                    List<User> students = course.getUsers()
                            .stream()
                            .filter(user -> user.getRole() == User.Role.STUDENT) // Filter for students
                            .toList();
                    return ResponseEntity.ok(students);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        boolean loginSuccess = userService.login(username, password);
        Map<String, Object> response = new HashMap<>();
        response.put("username", username);

        if (loginSuccess) {
            response.put("message", "Login successful");
            //response.put("redirectUrl", "/api/users/home?username=" + username);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        // Create a new User
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        user.setEmail(userRequest.getEmail());
        user.setCreatedAt(userRequest.getCreatedAt());

        // Fetch courses from the database using provided course IDs
        if (userRequest.getCourseIds() != null && !userRequest.getCourseIds().isEmpty()) {
            List<Course> courses = courseRepository.findAllById(userRequest.getCourseIds());
            user.setCourses(courses); // Associate courses with the user
        }

        // Save the user and return the response
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }


    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userService.saveUser(user); // Update the user
        return "success"; // Redirect to user list page after update
    }


    // Delete user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "success";
    }




}



