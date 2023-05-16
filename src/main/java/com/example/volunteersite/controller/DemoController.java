package com.example.volunteersite.controller;

import com.example.volunteersite.dto.PostDto;
import com.example.volunteersite.exeption.UserRatingException;
import com.example.volunteersite.service.PostService;
import com.example.volunteersite.service.UserServiceImpl;
import com.example.volunteersite.user.Post;
import com.example.volunteersite.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:3000")
public class DemoController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/addUserOnPost")
    public ResponseEntity<String> addUserOnPost(@Valid @NotNull @RequestParam Long id) throws UserRatingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userService.findByEmail(currentUserName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        postService.addUserOnPost(id, user);
        return ResponseEntity.ok("User added to post successfully.");
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
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }

    @GetMapping("/findPostBy/{id}")
    public Post findPostById (@PathVariable long id) {
        return postService.findById(id);
    }
}