package com.example.volunteersite.auth;

import com.example.volunteersite.config.JwtService;
import com.example.volunteersite.repositories.OrganizationRepository;
import com.example.volunteersite.user.Organization;
import com.example.volunteersite.user.Role;
import com.example.volunteersite.user.User;
import com.example.volunteersite.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final OrganizationRepository organizationRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) throws Exception {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .rating(90)
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .city(request.getCity())
                .role(Role.USER)
                .build();
        if(organizationRepository.findByEmail(request.getEmail()).equals(request.getEmail())){
            throw new Exception("We have same user's and organization's email");
        }
        else if (repository.findByEmail(request.getEmail()).equals(request.getEmail())){
            throw new Exception("We have user with the same email");
        }
        else {
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse registerOrg(RegisterOrgRequest request) throws Exception {
        Organization organization = new Organization();
        if(repository.findByEmail(request.getEmail()).equals(request.getEmail())){
            throw new Exception("We have same user's and organization's email");
        }
        else if(organizationRepository.findByEmail(request.getEmail()).equals(request.getEmail())){
            throw new Exception("We have organization with the same email");
        }
        else if(!repository.findByEmail(request.getEmail()).equals(request.getEmail()) && !organizationRepository.findByEmail(request.getEmail()).equals(request.getEmail())){
            organization.setActivity(request.getActivity());
            organization.setOrganizationName(request.getOrganizationName());
            organization.setEmail(request.getEmail());
            organization.setAdress(request.getAdress());
            organization.setPassword(passwordEncoder.encode(request.getPassword()));
            organization.setRole(Role.ORGANIZATION);
            organization.setNumber(request.getNumber());
        organizationRepository.save(organization);
        }
        var jwtToken = jwtService.generateToken(organization);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateOrg(AuthenticationOrgRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var organization = organizationRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(organization);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}
