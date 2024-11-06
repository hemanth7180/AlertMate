package com.project.public_safety_app.repository;

import com.project.public_safety_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
    Optional<User> findByEmail(String email);
}
