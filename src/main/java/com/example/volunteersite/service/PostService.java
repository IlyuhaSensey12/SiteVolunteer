package com.example.volunteersite.service;

import com.example.volunteersite.dto.PostDto;
import com.example.volunteersite.repositories.PostRepository;
import com.example.volunteersite.user.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public void save(PostDto postDto){
        Post post = new Post();
        post.setDescription(postDto.getDescription());
        postRepository.save(post);
    }


}
