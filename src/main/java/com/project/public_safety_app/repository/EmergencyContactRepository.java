package com.project.public_safety_app.repository;

import com.project.public_safety_app.model.EmergencyContacts;
import com.project.public_safety_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContacts,Long> {
    List<EmergencyContacts> findByUser(User user);
}
