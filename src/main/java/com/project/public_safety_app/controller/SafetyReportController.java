package com.project.public_safety_app.controller;

import com.project.public_safety_app.model.Discussion;
import com.project.public_safety_app.model.SafetyReport;
import com.project.public_safety_app.model.User;
import com.project.public_safety_app.service.SafetyReportService;
import com.project.public_safety_app.service.UserService;
import com.project.public_safety_app.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reports")
public class SafetyReportController {
    @Autowired
    private SafetyReportService safetyReportService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<SafetyReport> createReport(@RequestBody SafetyReport report,@RequestParam String userName) {
        User user= EntityUtil.convertToEntity(userService.getUserByName(userName));
        SafetyReport createdReport = safetyReportService.createReport(report,user);
        return ResponseEntity.ok(createdReport);
    }

    @GetMapping
    public ResponseEntity<List<SafetyReport>> getAllReports() {
        List<SafetyReport> reports = safetyReportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        safetyReportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/safetyReports")
    public ResponseEntity<List<SafetyReport>> getSafetyReportsOfUser(@RequestParam String userName){
       List<SafetyReport> safetyReports= safetyReportService.getReportsByUser(userName);
       return ResponseEntity.ok(safetyReports);
    }
    @GetMapping("/user")
    public ResponseEntity<List<SafetyReport>> getSafetyReportsByUser(@RequestParam String userName){
        return ResponseEntity.ok(safetyReportService.getSafetyReportsBYUserName(userName));
    }
}
