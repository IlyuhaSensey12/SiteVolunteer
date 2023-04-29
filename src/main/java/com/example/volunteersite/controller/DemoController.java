package com.example.volunteersite.controller;

import com.example.volunteersite.dto.PostDto;
import com.example.volunteersite.service.PostService;
import com.example.volunteersite.user.Post;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:3000")
public class DemoController {
    @Autowired
    private PostService postService;
    @PostMapping("/addUserOnPost")
    public void addUserOnPost(long id, String name, String lastname) throws Exception {
        postService.addUserOnPost(id, name, lastname);
    }
    @GetMapping("/showAllPosts")
    public List<Post> showPostData(){
        return postService.findAll();
    }

    @PostMapping("/addPost")
    public void add(@RequestBody PostDto postDto){
        postService.save(postDto);
    }


    @PutMapping("/updatePost")
    public void updatePostByUserId(@RequestBody PostDto postDto){
        postService.editPost(postDto);
    }

    @DeleteMapping("/deletePost/{id}")
    public void deletePost(@PathVariable long id){
        postService.deletePost(id);
    }

}