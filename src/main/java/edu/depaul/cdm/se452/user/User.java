package edu.depaul.cdm.se452.user;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue
	private long id;
	
	private String userName;
	
	private String password;
	
	private Role role;
	
	private String email;
	
	private LocalDateTime createdAt;
	
	private Profile profile;
	
	public enum Role {
        STUDENT, INSTRUCTOR
    }
}
