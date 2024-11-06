package com.project.public_safety_app.service;

import com.project.public_safety_app.model.SelfAssessmentResult;
import com.project.public_safety_app.model.User;

import java.util.List;

public interface SelfAssessmentResultService {
    SelfAssessmentResult createResult(SelfAssessmentResult result,User user);

//    List<SelfAssessmentResult> getResultsByUser(User userId);

    void deleteResult(Long id);
}
