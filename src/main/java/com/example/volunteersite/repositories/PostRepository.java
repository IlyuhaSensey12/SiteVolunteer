package com.example.volunteersite.repositories;

import com.example.volunteersite.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
