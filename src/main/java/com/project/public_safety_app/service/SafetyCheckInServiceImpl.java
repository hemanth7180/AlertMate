package com.project.public_safety_app.service;

import com.project.public_safety_app.model.SafetyCheckIn;

import com.project.public_safety_app.model.User;
import com.project.public_safety_app.repository.SafetyCheckInRepository;
import com.project.public_safety_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SafetyCheckInServiceImpl implements SafetyCheckInService{

    @Autowired
    private SafetyCheckInRepository safetyCheckInRepository;

    @Autowired
    private UserRepository userRepository;

    public SafetyCheckIn createCheckIn(SafetyCheckIn checkIn,User user) {
        // Check if the user is new or existing
//        if (userRepository.findByUserName(user.getUserName()) == null) {
//            userRepository.save(user);
//        }

        // Set the user for the check-in
        checkIn.setUser(user);

        // Save the check-in to ensure it has a valid ID
        SafetyCheckIn savedCheckIn = safetyCheckInRepository.save(checkIn);

        // Manage the bidirectional relationship
        if (user.getSafetyCheckIns() == null) {
            user.setSafetyCheckIns(new ArrayList<>()); // Initialize if null
        }
        user.getSafetyCheckIns().add(savedCheckIn); // Add the saved check-in to the user's list

        // Save the user to maintain the relationship
        userRepository.save(user);

        return savedCheckIn;

    }

    public List<SafetyCheckIn> getCheckInsByUser(String userName) {
        User user=userRepository.findByUserName(userName);
        return user.getSafetyCheckIns(); // Custom method to be implemented
    }

    public void deleteCheckIn(Long id) {
        safetyCheckInRepository.deleteById(id);
    }

    public List<SafetyCheckIn> getSafetyCheckInBYUserName(String userName){
        User user=userRepository.findByUserName(userName);
        return user.getSafetyCheckIns();
    }

}
