package com.project.public_safety_app.service;

import com.project.public_safety_app.model.SafetyReport;
import com.project.public_safety_app.model.User;
import com.project.public_safety_app.repository.SafetyReportRepository;
import com.project.public_safety_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SafetyReportServiceImpl implements SafetyReportService{

    @Autowired
    private SafetyReportRepository safetyReportRepository;

    @Autowired
    private UserRepository userRepository;

    public SafetyReport createReport(SafetyReport report,User user) {

        if (userRepository.findByUserName(user.getUserName()) == null) {
            userRepository.save(user);
        }

        // Set the user in the report
        report.setUser(user);

        // Save the report to ensure it has a valid ID
        SafetyReport savedReport = safetyReportRepository.save(report);

        // Manage the bidirectional relationship
        if (user.getSafetyReports() == null) {
            user.setSafetyReports(new ArrayList<>());
        }
        user.getSafetyReports().add(savedReport); // Add the saved report to the user's list

        // Save the user to maintain the relationship
        userRepository.save(user);

        return savedReport;
    }

    public List<SafetyReport> getAllReports() {
        return safetyReportRepository.findAll();
    }

    public List<SafetyReport> getReportsByUser(String userName) {
        User user=userRepository.findByUserName(userName);
        return user.getSafetyReports(); // Custom method to be implemented
    }

    public void deleteReport(Long id) {
        safetyReportRepository.deleteById(id);
    }

    public List<SafetyReport> getSafetyReportsBYUserName(String userName){
        User user=userRepository.findByUserName(userName);
        return user.getSafetyReports();
    }

}


