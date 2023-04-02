package com.example.volunteersite;

import com.example.volunteersite.dto.PostDto;
import com.example.volunteersite.service.PostService;
import com.example.volunteersite.user.Post;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class DemoController {
    @Autowired
    private PostService postService;
    @GetMapping("/demo")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from endpoint");
    }

    @PostMapping("/addPost")
    public void add(@RequestBody PostDto postDto){
        postService.save(postDto);
    }
}
