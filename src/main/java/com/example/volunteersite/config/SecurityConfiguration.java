package com.example.volunteersite.config;

import com.example.volunteersite.user.Role;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Autowired
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**",
                        "/api/v1/showAllOrgs",
                        "/api/v1/showAllCategories",
                        "/api/v1/showCategoryByName",
                        "/api/v1/showAllPosts",
                        "/api/v1/findOrgById/{id}",
                        "/api/v1/showAllUsers",
                        "/api/v1/addImage/{id}",
                        "/api/v1/getUser",
                        "api/v1/getOrganization",
                        "api/v1/findPostBy/{id}")
                .permitAll()
                .requestMatchers("/saveCategory",
                        "/updateOrg",
                        "/deleteOrg/{id}",
                        "/addPost",
                        "/updatePost",
                        "/deletePost/{id}")
                .hasAnyAuthority(String.valueOf(Role.ORGANIZATION), String.valueOf(Role.ADMIN))
                .requestMatchers("/updateUser",
                        "/deleteUser/{id}")
                .hasAnyAuthority(String.valueOf(Role.USER), String.valueOf(Role.ADMIN))
                .requestMatchers("/addUserOnPost").hasAuthority(String.valueOf(Role.USER))
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
