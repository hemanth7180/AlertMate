package com.project.public_safety_app.controller;

import com.project.public_safety_app.model.Discussion;
import com.project.public_safety_app.model.SafetyCheckIn;
import com.project.public_safety_app.model.User;
import com.project.public_safety_app.service.SafetyCheckInService;
import com.project.public_safety_app.service.UserService;
import com.project.public_safety_app.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/check-ins")
public class SafetyCheckInController {
    @Autowired
    private SafetyCheckInService safetyCheckInService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<SafetyCheckIn> createCheckIn(@RequestBody SafetyCheckIn checkIn,@RequestParam String userName) {
        User user= EntityUtil.convertToEntity(userService.getUserByName(userName));
        SafetyCheckIn createdCheckIn = safetyCheckInService.createCheckIn(checkIn,user);
        return ResponseEntity.ok(createdCheckIn);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SafetyCheckIn>> getCheckInsByUserId(@RequestParam String UserName) {
        List<SafetyCheckIn> checkIns = safetyCheckInService.getCheckInsByUser(UserName);
        return ResponseEntity.ok(checkIns);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckIn(@PathVariable Long id) {
        safetyCheckInService.deleteCheckIn(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    public ResponseEntity<List<SafetyCheckIn>> getSafetyCheckInByUser(@RequestParam String userName){
        return ResponseEntity.ok(safetyCheckInService.getSafetyCheckInBYUserName(userName));
    }
}

