package edu.depaul.cdm.se452.user;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserRequest {
    private String userName;
    private String password;
    private User.Role role;
    private String email;
    private LocalDateTime createdAt;
    private List<Long> courseIds; // List of course IDs to associate with the user
}