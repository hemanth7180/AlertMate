package com.project.public_safety_app.repository;

import com.project.public_safety_app.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    // Custom query methods can be added here
}
