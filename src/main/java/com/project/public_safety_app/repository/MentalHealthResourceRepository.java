package com.project.public_safety_app.repository;

import com.project.public_safety_app.model.MentalHealthResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentalHealthResourceRepository extends JpaRepository<MentalHealthResource, Long> {
    // Custom query methods can be added here
}
