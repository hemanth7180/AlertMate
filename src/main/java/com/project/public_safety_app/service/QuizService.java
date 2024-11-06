package com.project.public_safety_app.service;

import com.project.public_safety_app.model.Quiz;
import com.project.public_safety_app.model.User;

import java.util.List;

public interface QuizService {
    Quiz createQuiz(Quiz quiz, User user);

    List<Quiz> getAllQuizzes();

    void deleteQuiz(Long id);
    List<Quiz> getQuizBYUserName(String userName);
}
