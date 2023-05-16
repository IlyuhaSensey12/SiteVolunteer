package com.example.volunteersite.dto;

import com.example.volunteersite.user.Post;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private long id;
    private String name;
    private List<Post> posts;
}
