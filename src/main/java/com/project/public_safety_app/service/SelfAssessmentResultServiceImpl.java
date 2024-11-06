package com.project.public_safety_app.service;

import com.project.public_safety_app.model.Quiz;
import com.project.public_safety_app.model.SelfAssessmentResult;
import com.project.public_safety_app.model.User;
import com.project.public_safety_app.repository.SelfAssessmentResultRepository;
import com.project.public_safety_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfAssessmentResultServiceImpl implements SelfAssessmentResultService{

    @Autowired
    private SelfAssessmentResultRepository selfAssessmentResultRepository;

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserRepository userRepository;

    public SelfAssessmentResult createResult(SelfAssessmentResult result,User user) {

        Quiz quiz=result.getQuiz();
        result.setQuiz(quiz);

//        return safetyCheckInRepository.save(checkIn);
        return selfAssessmentResultRepository.save(result);
    }


    public void deleteResult(Long id) {
        selfAssessmentResultRepository.deleteById(id);
    }

}
