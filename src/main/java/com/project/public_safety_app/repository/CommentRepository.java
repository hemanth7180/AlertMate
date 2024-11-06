package com.project.public_safety_app.repository;


import com.project.public_safety_app.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Get comments by discussion ID
    List<Comment> findByDiscussionId(Long discussionId);
//    void deleteByDiscussionId(Long discussionId);
}
