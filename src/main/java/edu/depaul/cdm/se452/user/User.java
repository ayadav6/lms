package edu.depaul.cdm.se452.user;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.depaul.cdm.se452.course.Course;
import jakarta.persistence.*;
import lombok.Data;


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

//	@OneToMany(mappedBy = "instructor")
//	private List<Course> courseList;


	@ManyToMany
	@JsonManagedReference
	@JoinTable(
			name = "user_courses", // Name of the join table
			joinColumns = @JoinColumn(name = "user_id"), // Foreign key for User
			inverseJoinColumns = @JoinColumn(name = "course_id") // Foreign key for Course
	)
	private List<Course> courses;

	public enum Role {
		STUDENT, INSTRUCTOR
	}
}
