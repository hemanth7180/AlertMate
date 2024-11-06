package com.project.public_safety_app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.public_safety_app.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @NotNull(message = "User cannot be null")
    @JsonIgnore
    private User user;

    @NotBlank(message = "Topic cannot be empty")
    @Size(max = 100, message = "Topic cannot exceed 100 characters")
    private String topic;

    @NotBlank(message = "Content cannot be empty")
    private String content;

//
}
