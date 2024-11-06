package com.project.public_safety_app.service;

import com.project.public_safety_app.model.SafetyReport;
import com.project.public_safety_app.model.User;

import java.util.List;

public interface SafetyReportService {
    SafetyReport createReport(SafetyReport report,User user);

    List<SafetyReport> getAllReports();

    List<SafetyReport> getReportsByUser(String userId);

    void deleteReport(Long id);

    List<SafetyReport> getSafetyReportsBYUserName(String userName);
}
