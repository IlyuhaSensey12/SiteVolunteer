package com.example.volunteersite.dto;

import lombok.Data;

@Data
public class OrgDto {
    private long id;
    private String organizationName;
    private String activity;
    private String adress;
    private String number;
    private String email;
    private String password;
}
