package com.project.public_safety_app.controller;


import com.project.public_safety_app.model.Discussion;
import com.project.public_safety_app.model.Quiz;
import com.project.public_safety_app.model.User;
import com.project.public_safety_app.service.QuizService;
import com.project.public_safety_app.service.UserService;
import com.project.public_safety_app.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz,@RequestParam String userName) {
        User user = EntityUtil.convertToEntity(userService.getUserByName(userName));
        Quiz createdQuiz = quizService.createQuiz(quiz,user);
        return ResponseEntity.ok(createdQuiz);
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    public ResponseEntity<List<Quiz>> getQuizByUser(@RequestParam String userName){
        return ResponseEntity.ok(quizService.getQuizBYUserName(userName));
    }
}
