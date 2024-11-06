package com.project.public_safety_app.service;

import com.project.public_safety_app.model.MentalHealthResource;
import com.project.public_safety_app.model.User;

import java.util.List;

public interface MentalHealthResourceService {
    MentalHealthResource createResource(MentalHealthResource resource, User user);

    List<MentalHealthResource> getAllResources();

    void deleteResource(Long id);
}
