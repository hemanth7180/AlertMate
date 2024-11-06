package com.project.public_safety_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyContacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long emergency_id;

    @ManyToOne // This ensures the user cannot be null
//    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    //    @NotBlank(message = "Contact name is required")
    private String contactName;

    //    @NotNull(message = "Contact phone number is required")
    private String contactPhoneNumber;

}
