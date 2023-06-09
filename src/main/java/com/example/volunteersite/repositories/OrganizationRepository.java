package com.example.volunteersite.repositories;

import com.example.volunteersite.user.Organization;
import com.example.volunteersite.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findByEmail(String email);

    Organization findById(long id);
}
