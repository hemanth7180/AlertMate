package com.project.public_safety_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "self_assessment_results")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelfAssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;


    @ManyToOne
    @JsonIgnore
    private Quiz quiz;

    private int score;
    private String feedback;
}
