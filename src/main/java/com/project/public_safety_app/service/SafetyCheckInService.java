package com.project.public_safety_app.service;

import com.project.public_safety_app.model.SafetyCheckIn;
import com.project.public_safety_app.model.User;

import java.util.List;

public interface SafetyCheckInService {
    SafetyCheckIn createCheckIn(SafetyCheckIn checkIn,User user);

    List<SafetyCheckIn> getCheckInsByUser(String userName);

    void deleteCheckIn(Long id);

    List<SafetyCheckIn> getSafetyCheckInBYUserName(String userName);
}
