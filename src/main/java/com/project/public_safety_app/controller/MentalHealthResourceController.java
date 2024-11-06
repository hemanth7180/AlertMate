package com.project.public_safety_app.controller;

import com.project.public_safety_app.model.MentalHealthResource;
import com.project.public_safety_app.model.User;
import com.project.public_safety_app.service.MentalHealthResourceService;
import com.project.public_safety_app.service.UserService;
import com.project.public_safety_app.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/mental-health-resources")
public class MentalHealthResourceController {
    @Autowired
    private MentalHealthResourceService mentalHealthResourceService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<MentalHealthResource> createResource(@RequestBody MentalHealthResource resource,@RequestParam String userName) {
        User user= EntityUtil.convertToEntity(userService.getUserByName(userName));
        MentalHealthResource createdResource = mentalHealthResourceService.createResource(resource,user);
        return ResponseEntity.ok(createdResource);
    }

    @GetMapping
    public ResponseEntity<List<MentalHealthResource>> getAllResources() {
        List<MentalHealthResource> resources = mentalHealthResourceService.getAllResources();
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        mentalHealthResourceService.deleteResource(id);
        return ResponseEntity.noContent().build();
    }
}

