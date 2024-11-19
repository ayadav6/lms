package edu.depaul.cdm.se452.user;

import edu.depaul.cdm.se452.course.Course;
import edu.depaul.cdm.se452.course.CourseRepository;
import edu.depaul.cdm.se452.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/users")
public class UserViewController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    CourseService courseService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//        @PostMapping
//        public ResponseEntity<User> createUser(@RequestBody User user) {
//            User createdUser = userService.saveUser(user);
//            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//        }




    @GetMapping("/listUsers")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/user-list"; // Points to templates/user/user-list.html
    }

    @GetMapping("/loginView")
    public String showLoginPage() {
        return "user/login";
    }

    @GetMapping("/home")
    public String showHomePage( String userName, Model model) {
        model.addAttribute("userName", userName);
        return "user/home-page";
    }

    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("courses", courseService.getAllCourses()); // Pass the list of courses
        return "user/signup";
    }

    @GetMapping("/details")
    public String userDetails(Model model, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user/userdetails";
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


    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        List<Course> courses = courseService.getAllCourses(); // Fetch all available courses
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            model.addAttribute("courses", courses);
            return "user/edit-user"; // Points to templates/user/edit-user.html
        } else {
            return "redirect:/users/list";
        }
    }

//    @GetMapping("/edit/{id}")
//    public String editUser(@PathVariable("id") Long id, Model model) {
//        Optional<User> user = userService.getUserById(id);
//        List<Course> courses = courseService.getAllCourses(); // Fetch all available courses
//        model.addAttribute("user", user);
//        model.addAttribute("courses", courses);
//        return "edit-user";
//    }


    @GetMapping("/showCreateUserForm")
    public String showCreateUserForm(Model model) {
        System.out.println("Accessing showCreateUserForm");
        model.addAttribute("user", new User());
        model.addAttribute("courses", courseRepository.findAll()); // Load all courses
        return "user/user-create"; // This is the name of the new HTML file
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDTO userDto) {
        // Update user logic here
        userService.updateUser(userDto);

        return "redirect:/api/users/listUsers";
    }


    // Delete user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/api/users/listUsers";
    }



}
