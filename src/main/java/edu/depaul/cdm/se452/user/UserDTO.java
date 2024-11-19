package edu.depaul.cdm.se452.user;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Long id;
    private String userName;
    private String email;
    private String password;
    private User.Role role;
    private List<Long> courseIds;
}
