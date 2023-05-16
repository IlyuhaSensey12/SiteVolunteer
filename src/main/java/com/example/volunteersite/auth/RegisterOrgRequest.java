package com.example.volunteersite.auth;

import lombok.Data;

@Data
public class RegisterOrgRequest {
    private String organizationName;
    private String activity;
    private String adress;
    private String number;
    private String email;
    private String password;
}
