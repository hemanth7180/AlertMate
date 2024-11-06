package com.project.public_safety_app.repository;

import com.project.public_safety_app.model.SafetyCheckIn;
import com.project.public_safety_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SafetyCheckInRepository extends JpaRepository<SafetyCheckIn, Long> {
    List<SafetyCheckIn> findByUser(User userId);
    // Custom query methods can be added here
}
