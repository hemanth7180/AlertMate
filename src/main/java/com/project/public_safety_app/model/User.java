package com.project.public_safety_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_table ")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    //    @NotBlank(message = "Username is required")
    private String userName;
    //    @NotNull(message = "Date of birth is required")
    private LocalDate DOB;
    //    @Email(message = "Email should be valid")
//    @NotBlank(message = "Email is required")
    private String email;
    //    @NotNull(message = "Phone number should not be empty")
//    @Size(min = 10, max = 10)
    private String userPhoneNumber;
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
//    @NotNull(message = "Emergency contacts are required")
    private List<EmergencyContacts> contacts;
    //    @NotBlank(message = "Password is required")
//    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Discussion> discussions;

    @OneToMany
    private List<SafetyCheckIn> safetyCheckIns;

    @OneToMany
    private List<SafetyReport> safetyReports;

    @OneToMany
    private List<Quiz> quizzes;


}
