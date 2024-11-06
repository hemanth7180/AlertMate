package com.project.public_safety_app.dto;

import com.project.public_safety_app.controller.*;
import com.project.public_safety_app.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Relation(collectionRelation = "users")
public class UserResponse extends RepresentationModel<UserResponse> {

    private long userId;
    private String userName;
    private String email;
    private String userPhoneNumber;
    private String password; // Consider not exposing password in the response for security
    private List<EmergencyContacts> contacts;
    private List<Discussion> discussions;
    private List<SafetyCheckIn> safetyCheckIns;
    private List<SafetyReport> safetyReports;
    private List<Quiz> quizzes;
    private LocalDate DOB;

    // Method to add HATEOAS links to the response
    public void addLinks(String userId) {
        // Self link (current user)
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUserByName(userId)).withSelfRel());

        // Links for related resources
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(QuizController.class).getQuizByUser(userId)).withRel("quizzes"));
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DiscussionController.class).getDiscussionByUser(userId)).withRel("discussions"));
//        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmergencyContactController.class).getEmergencyContactsByUserId(userId)).withRel("contacts"));
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SafetyCheckInController.class).getSafetyCheckInByUser(userId)).withRel("safetyCheckIns"));
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SafetyReportController.class).getSafetyReportsByUser(userId)).withRel("safetyReports"));
    }
}
