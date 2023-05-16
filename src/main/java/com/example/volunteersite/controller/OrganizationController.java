package com.example.volunteersite.controller;

import com.example.volunteersite.dto.OrgDto;
import com.example.volunteersite.service.OrganizationService;
import com.example.volunteersite.user.Organization;
import com.example.volunteersite.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:3000")
public class OrganizationController {
    @Autowired
    private OrganizationService userService;

    @GetMapping("/getOrganization")
    public Optional<Organization> getOrg() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentOrg = authentication.getName();
        return userService.findByEmail(currentOrg);
    }
    @GetMapping("/findOrgById/{id}")
    public Organization showOrg(@PathVariable long id){
        return userService.findById(id);
    }

    @GetMapping("/showAllOrgs")
    public List<Organization> showUserData(){
        return userService.findAllOrg();
    }

    @PutMapping("/updateOrg")
    public void updateUser(@RequestBody OrgDto orgDto){
        userService.editOrg(orgDto);
    }

    @DeleteMapping("/deleteOrg/{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteOrg(id);
    }
}
