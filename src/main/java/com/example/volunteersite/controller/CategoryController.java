package com.example.volunteersite.controller;

import com.example.volunteersite.dto.CategoryDto;
import com.example.volunteersite.service.CategoryService;
import com.example.volunteersite.user.Category;
import com.example.volunteersite.user.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/showAllCategories")
    public List<Category> showCategories(){
        return  categoryService.findAllCategories();
    }

    @GetMapping("/showCategoryByName")
    public Category showCategory(String name){
        return categoryService.findByNameCategory(name);
    }

    @PostMapping("/saveCategory")
    public void addCategory(@RequestBody CategoryDto categoryDto){
        categoryService.save(categoryDto);
    }
}