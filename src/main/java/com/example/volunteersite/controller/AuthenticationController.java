package com.example.volunteersite.controller;

import com.example.volunteersite.auth.*;
import com.example.volunteersite.auth.RegisterOrgRequest;
import com.example.volunteersite.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) throws Exception {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/registerOrg")
    public ResponseEntity<AuthenticationResponse> registerOrg(
            @RequestBody RegisterOrgRequest request
    ) throws Exception {
        return ResponseEntity.ok(service.registerOrg(request));
    }

    @PostMapping("/authenticateOrg")
    public ResponseEntity<AuthenticationResponse> authenticateOrg(
            @RequestBody AuthenticationOrgRequest request
    ){
        return ResponseEntity.ok(service.authenticateOrg(request));
    }



}
