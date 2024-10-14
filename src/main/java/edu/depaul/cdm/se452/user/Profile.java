package edu.depaul.cdm.se452.user;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Profile {
	
	@Id
	@GeneratedValue
	private long id;
	
	
	
}
