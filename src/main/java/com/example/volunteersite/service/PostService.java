package com.example.volunteersite.service;

import com.example.volunteersite.dto.PostDto;
import com.example.volunteersite.exeption.UserRatingException;
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

    @Autowired
    private UserRepository userRepository;

    public void addUserOnPost(Long id, User user) throws UserRatingException {
        Post post = postRepository.findPostById(id);
        int requiredRating = post.getRatingOfVolunteer();
        int userRating = user.getRating();
        if (userRating < requiredRating) {
            throw new UserRatingException("User's rating is not sufficient for this post");
        } else {
            int newRating = userRating + 10;
            user.setRating(newRating);
            post.getUsers().add(user);
            postRepository.save(post);
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
