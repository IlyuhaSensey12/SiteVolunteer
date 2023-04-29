package com.example.volunteersite.dto;

import com.example.volunteersite.user.Category;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private int count;
    private Category category;
    private String timeAndPlace;

    private int ratingOfVolunteer;
}
