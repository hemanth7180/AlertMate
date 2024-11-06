package com.project.public_safety_app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.project.public_safety_app.model.user.User;
import com.project.public_safety_app.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContactDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long emergency_id;

    @ManyToOne // This ensures the user cannot be null
//    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @NotBlank(message = "Contact name is required")
    private String contactName;

    @NotNull(message = "Contact phone number is required")
    private String contactPhoneNumber;
}
