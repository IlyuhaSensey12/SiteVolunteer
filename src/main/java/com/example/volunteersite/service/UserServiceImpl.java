package com.example.volunteersite.service;

import com.example.volunteersite.dto.PostDto;
import com.example.volunteersite.dto.UserDto;
import com.example.volunteersite.repositories.PostRepository;
import com.example.volunteersite.repositories.UserRepository;
import com.example.volunteersite.user.Post;
import com.example.volunteersite.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }


    public void editUser(UserDto userDto){
        User user = userRepository.findById(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setRole(userDto.getRole());
        user.setRating(userDto.getRating());
        userRepository.save(user);
    }

    public void addImageToUser(MultipartFile file){
        Optional<User> user = userRepository.findByEmail(getCurrentUser());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")){
            System.out.println("not a valid file");
        }
        try{
            user.get().setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        }catch (IOException e){
            e.printStackTrace();
        }
        userRepository.save(user.get());
    }

    public void deleteUser(long id){
        User user = userRepository.findById(id);
        userRepository.delete(user);
    }

    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = (String) authentication.getName();
        return currentUserName;
    }

    public User findByFirstname(String name){
        return userRepository.findByFirstname(name);
    }

    public List<User> findAllByLastName(String lastname){
        return userRepository.findAllByLastname(lastname);
    }

    public User findById(long id) {
        return userRepository.findById(id);
    }

}
