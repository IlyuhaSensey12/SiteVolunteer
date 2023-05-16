package com.example.volunteersite.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name ="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String title;
    private int count;
    private String timeAndPlace;
//    @Min(value = 1, message = "Cannot be zero")
//    @Max(value = 100, message = "Invalid value of rating")

    private int ratingOfVolunteer;

    @ManyToMany
    @JoinTable(name = "posts_users",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @ManyToOne
    private Category category;
}
