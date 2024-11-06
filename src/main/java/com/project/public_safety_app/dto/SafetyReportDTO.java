package com.project.public_safety_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SafetyReportDTO {

    private Long id;

    @NotNull(message = "User ID cannot be null")
    private Long userId; // Assuming you'll pass just the user ID

    @NotBlank(message = "Incident details cannot be empty")
    private String incidentDetails;

    private LocalDateTime createdAt;
}
