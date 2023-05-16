package com.example.volunteersite.dto;

import com.example.volunteersite.user.Role;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String firstname;

    private String lastname;

    private String email;

    private String password;
    private Role role;

    private int rating;
}
