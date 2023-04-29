package com.example.volunteersite.service;

import com.example.volunteersite.dto.PostDto;
import com.example.volunteersite.repositories.PostRepository;
import com.example.volunteersite.repositories.UserRepository;
import com.example.volunteersite.user.Post;
import com.example.volunteersite.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired UserRepository userRepository;

    public void addUserOnPost(long id, String name, String lastname) throws Exception {
        Post post = postRepository.findById(id);
        User user = userRepository.findByFirstnameAndLastname(name, lastname);
        if(user.getRating() < post.getRatingOfVolunteer()){
            throw new Exception("User's rating isn't suitable");
        }else {
            user.setRating(user.getRating() + 5);
            post.getUsers().add(user);
        }
    }


    public void save(PostDto postDto){
        Post post = new Post();
        post.setDescription(postDto.getDescription());
        post.setCount(postDto.getCount());
        post.setTitle(postDto.getTitle());
        post.setTimeAndPlace(postDto.getTimeAndPlace());
        post.setCategory(postDto.getCategory());
        post.setRatingOfVolunteer(post.getRatingOfVolunteer());
        postRepository.save(post);
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public void editPost(PostDto postDto){
        Post post = postRepository.findById(postDto.getId());
        post.setCount(postDto.getCount());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
        post.setTimeAndPlace(postDto.getTimeAndPlace());
        post.setCategory(postDto.getCategory());
        post.setRatingOfVolunteer(post.getRatingOfVolunteer());
        postRepository.save(post);
    }

    public void deletePost(long id){
        Post post = postRepository.findPostById(id);
        postRepository.delete(post);
    }

    public Post findById(long id) {
        return postRepository.findById(id);
    }

}
