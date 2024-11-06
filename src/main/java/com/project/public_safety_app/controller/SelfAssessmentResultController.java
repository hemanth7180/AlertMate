package com.project.public_safety_app.controller;

import com.project.public_safety_app.model.SelfAssessmentResult;
import com.project.public_safety_app.model.User;
import com.project.public_safety_app.service.SelfAssessmentResultService;
import com.project.public_safety_app.service.UserService;
import com.project.public_safety_app.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

//@RestController
//@RequestMapping("/api/self-assessments")
public class SelfAssessmentResultController {
//    @Autowired
//    private SelfAssessmentResultService selfAssessmentResultService;
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping
//    public ResponseEntity<SelfAssessmentResult> createResult(@RequestBody SelfAssessmentResult result,@RequestParam String userName) {
//        User user = EntityUtil.convertToEntity(userService.getUserByName(userName));
//
//        // Check if the user exists
////        if (user == null) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND)
////                    .body(null); // Or return a meaningful error response
////        }
//
//        // Optionally convert to entity if necessary
////        User user = EntityUtil.convertToEntity(user);
//
//        // Validate the SelfAssessmentResult
//        // (Assuming you have a validation method)
//        if (!isValid(result)) {
//            return ResponseEntity.badRequest().body(null); // Return bad request if validation fails
//        }
//
//        // Create the SelfAssessmentResult
//        SelfAssessmentResult createdResult;
//        try {
//            createdResult = selfAssessmentResultService.createResult(result, user);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null); // Handle exceptions appropriately
//        }
//
//        // Return the created result
//        return ResponseEntity.ok(createdResult);
//    }
//
//    // Example validation method (implement as needed)
//    private boolean isValid(SelfAssessmentResult result) {
//        return result.getScore() >= 0; // Add more validation logic as necessary
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<SelfAssessmentResult>> getResultsByUserId(@RequestBody User user) {
//        List<SelfAssessmentResult> results = selfAssessmentResultService.getResultsByUser(user);
//        return ResponseEntity.ok(results);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
//        selfAssessmentResultService.deleteResult(id);
//        return ResponseEntity.noContent().build();
//    }
}

