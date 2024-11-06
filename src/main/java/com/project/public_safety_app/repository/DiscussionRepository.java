package com.project.public_safety_app.repository;

import com.project.public_safety_app.model.Discussion;
import com.project.public_safety_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    List<Discussion> findByUser(User user);
    // Custom query methods can be added here
}
