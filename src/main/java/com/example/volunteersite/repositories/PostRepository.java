package com.example.volunteersite.repositories;

import com.example.volunteersite.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();

    Post findPostById(long id);

    Post findById(long id);
}
