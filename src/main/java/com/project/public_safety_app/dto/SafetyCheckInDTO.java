package com.project.public_safety_app.dto;

import com.project.public_safety_app.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SafetyCheckInDTO {
    private Long id;

//    @NotNull(message = "User ID cannot be null")
    private User user; // Assuming you'll pass just the user ID

    @NotNull(message = "Check-in time cannot be null")
    private LocalDateTime checkInTime;

    @NotBlank(message = "Frequency cannot be empty")
    private String frequency;
}
