package com.project.public_safety_app.repository;

import com.project.public_safety_app.model.SelfAssessmentResult;
import com.project.public_safety_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SelfAssessmentResultRepository extends JpaRepository<SelfAssessmentResult, Long> {
    List<SelfAssessmentResult> findByUser(User userId);
    // Custom query methods can be added here
}
