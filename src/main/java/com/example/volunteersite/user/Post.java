package com.example.volunteersite.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
}
