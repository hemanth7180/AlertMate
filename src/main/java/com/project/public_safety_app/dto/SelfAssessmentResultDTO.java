package com.project.public_safety_app.dto;

import com.project.public_safety_app.model.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelfAssessmentResultDTO {
    private Long id;

    @ManyToOne
    private User userId; // Assuming you'll pass just the user ID

    @NotNull(message = "Quiz ID cannot be null")
    private Long quizId; // Assuming you'll pass just the quiz ID

    @Min(value = 0, message = "Score cannot be less than 0")
    @Max(value = 100, message = "Score cannot exceed 100")
    private int score;

    @NotBlank(message = "Feedback cannot be empty")
    private String feedback;
}
