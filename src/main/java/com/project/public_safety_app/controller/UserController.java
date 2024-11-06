package com.project.public_safety_app.controller;
import com.project.public_safety_app.dto.EmergencyContactDto;
import com.project.public_safety_app.dto.UserDto;
import com.project.public_safety_app.dto.UserResponse;
import com.project.public_safety_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/CreateUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteUser(@PathVariable String name) {
        userService.deleteUserByName(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/GetUser/{name}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable String name){
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @GetMapping("/{name}/contacts")
    public ResponseEntity<List<EmergencyContactDto>> getUserContacts(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUserContactsByUsername(name));
    }
    @GetMapping("/login")
    public ResponseEntity<UserDto> login(@RequestParam String userName, @RequestParam String password) {
        try {
            UserDto userDto = userService.login(userName, password);
            return ResponseEntity.ok(userDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userName) {
        // Retrieve the user and map it to UserResponse DTO
        UserResponse userResponse = userService.getUser(userName);

        // Add HATEOAS links for related resources
//        userResponse.addLinks(userName);

        // Return the user with HATEOAS links
        return ResponseEntity.ok(userResponse);
    }


}
