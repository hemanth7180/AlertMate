package com.project.public_safety_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "discussions")
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    private String topic;
    private String content;
    private int upvotes;
    private int downvotes;

    private boolean upvoted;
    private boolean downvoted;

    @OneToMany(mappedBy = "discussion",cascade = CascadeType.ALL)
    List<Comment> comments;

//    @Column(name = "created_at")
//    private LocalDateTime createdAt; // Optional for when the discussion was created

//    @OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL)
//    private List<DiscussionContent> contents; // Optional for discussion replies

    // Getters and Setters
}
