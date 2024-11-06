package com.project.public_safety_app.service;

import com.project.public_safety_app.model.MentalHealthResource;
import com.project.public_safety_app.model.User;
import com.project.public_safety_app.repository.MentalHealthResourceRepository;
import com.project.public_safety_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentalHealthResourceServiceImpl implements MentalHealthResourceService{

    @Autowired
    private MentalHealthResourceRepository mentalHealthResourceRepository;

    @Autowired
    private UserRepository userRepository;

    public MentalHealthResource createResource(MentalHealthResource resource, User user) {

        return mentalHealthResourceRepository.save(resource);
    }

    public List<MentalHealthResource> getAllResources() {
        return mentalHealthResourceRepository.findAll();
    }

    public void deleteResource(Long id) {
        mentalHealthResourceRepository.deleteById(id);
    }

}
