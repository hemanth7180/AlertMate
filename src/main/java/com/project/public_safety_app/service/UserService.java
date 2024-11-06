package com.project.public_safety_app.service;

import com.project.public_safety_app.dto.EmergencyContactDto;
import com.project.public_safety_app.dto.UserDto;
import com.project.public_safety_app.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userdto);
    UserDto login(String userName, String password);
    UserDto updateUser(Long userId, UserDto userDto);
    void deleteUser(Long userId);
    UserDto getUserByName(String name);
    void deleteUserByName(String name);
    List<EmergencyContactDto> getUserContactsByUsername(String username);
    List<UserDto> getAllUsers();
    UserResponse getUser(String userName);



}
