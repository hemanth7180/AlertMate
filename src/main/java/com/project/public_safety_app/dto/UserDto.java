package com.project.public_safety_app.dto;

import com.project.public_safety_app.model.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long userId;
    @NotBlank(message = "Username is required")
    private String userName;
    @NotNull(message = "Date of birth is required")
    private LocalDate DOB;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
    @NotNull(message = "Phone number should not be empty")
    private String userPhoneNumber;
    @OneToMany(mappedBy="User",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotNull(message = "Emergency contacts are required")
    private List<EmergencyContacts> contacts;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;
    @OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL)
    private List<Discussion> discussions; // Optional for discussion replies
    @OneToMany
    private List<SafetyCheckIn> safetyCheckIns;

    @OneToMany
    private List<SafetyReport> safetyReports;

    @OneToMany
    private List<Quiz> quizzes;

//    private List<String> links;
}