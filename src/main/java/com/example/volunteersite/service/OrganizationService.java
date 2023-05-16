package com.example.volunteersite.service;

import com.example.volunteersite.dto.OrgDto;
import com.example.volunteersite.repositories.OrganizationRepository;
import com.example.volunteersite.user.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    @Autowired
    private OrganizationRepository userRepository;

    public Optional<Organization> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<Organization> findAllOrg(){
        return userRepository.findAll();
    }

    public void editOrg(OrgDto orgDto){
        Organization organization = userRepository.findById(orgDto.getId());
        organization.setNumber(orgDto.getNumber());
        organization.setAdress(orgDto.getAdress());
        organization.setOrganizationName(orgDto.getOrganizationName());
        organization.setEmail(orgDto.getEmail());
        organization.setPassword(orgDto.getPassword());
        organization.setNumber(orgDto.getNumber());
        organization.setActivity(orgDto.getActivity());
        userRepository.save(organization);
    }

    public void deleteOrg(long id){
       Organization organization = userRepository.findById(id);
        userRepository.delete(organization);
    }

    public Organization findById(long id) {
        return userRepository.findById(id);
    }
}
