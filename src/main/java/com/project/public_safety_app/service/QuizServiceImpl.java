package com.project.public_safety_app.service;

import com.project.public_safety_app.model.Quiz;
import com.project.public_safety_app.model.SelfAssessmentResult;
import com.project.public_safety_app.model.User;
import com.project.public_safety_app.repository.QuizRepository;
import com.project.public_safety_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService{

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    public Quiz createQuiz(Quiz quiz, User user) {
        for (SelfAssessmentResult result : quiz.getResults()) {
            result.setQuiz(quiz);
        }

        // Check if the user is new or existing
        if (userRepository.findByUserName(user.getUserName()) == null) {
            // Handle the case where the user is transient (not saved yet)
            userRepository.save(user);
        }
        Quiz savedQuiz = quizRepository.save(quiz);

        // Now associate the saved quiz with the user
        if (user.getQuizzes() == null) {
            user.setQuizzes(new ArrayList<>());
        }
        user.getQuizzes().add(savedQuiz);
        savedQuiz.setUser(user); // Set the user in the quiz

        // Finally, save the user
        userRepository.save(user);

        return savedQuiz;

        // Finally, save the quiz
//        return quizRepository.save(quiz);

    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    public List<Quiz> getQuizBYUserName(String userName){
        User user=userRepository.findByUserName(userName);
        return user.getQuizzes();
    }

}
