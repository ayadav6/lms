package edu.depaul.cdm.se452.user;

import java.time.LocalDateTime;
import java.util.List;

import edu.depaul.cdm.se452.course.Course;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String userName;
	
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String email;
	
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "instructor")
	private List<Course> courseList;
	
	public enum Role {
        STUDENT, INSTRUCTOR
    }
}
