package com.project.public_safety_app.controller;

import com.project.public_safety_app.dto.CommentRequest;
import com.project.public_safety_app.model.Comment;
import com.project.public_safety_app.model.Discussion;
import com.project.public_safety_app.model.User;
import com.project.public_safety_app.service.DiscussionService;
import com.project.public_safety_app.service.UserService;
import com.project.public_safety_app.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/discussions")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Discussion> createDiscussion(@RequestBody Discussion discussion,@RequestParam String userName) {
        User user = EntityUtil.convertToEntity(userService.getUserByName(userName));
//        discussion.setUser(user);
        Discussion createdDiscussion = discussionService.createDiscussion(discussion,user);
        return ResponseEntity.ok(createdDiscussion);

    }

    @GetMapping
    public ResponseEntity<List<Discussion>> getAllDiscussions() {
        List<Discussion> discussions = discussionService.getAllDiscussions();
        return ResponseEntity.ok(discussions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscussion(@RequestParam String userName,@PathVariable Long id) {
        discussionService.deleteDiscussionByName(userName,id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    public ResponseEntity<List<Discussion>> getDiscussionByUser(@RequestParam String userName){
        return ResponseEntity.ok(discussionService.getDiscussionsByUser(userName));
    }

    @PostMapping("/{id}/reply")
    public ResponseEntity<Comment> replyToDiscussion(@PathVariable("id") Long discussionId, @RequestBody CommentRequest commentRequest) {
        Comment comment = discussionService.addComment(discussionId, commentRequest);
        if (comment != null) {
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET: api/discussions/{id}/comments
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable("id") Long discussionId) {
        List<Comment> comments = discussionService.getCommentsByDiscussionId(discussionId);
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // POST: api/discussions/{id}/upvote
    @PostMapping("/{id}/upvote")
    public ResponseEntity<String> upvoteDiscussion(@PathVariable("id") Long discussionId) {
        boolean success = discussionService.upvoteDiscussion(discussionId);
        if (success) {
            return new ResponseEntity<>("Discussion upvoted successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Discussion not found or already upvoted.", HttpStatus.NOT_FOUND);
    }

    // POST: api/discussions/{id}/downvote
    @PostMapping("/{id}/downvote")
    public ResponseEntity<String> downvoteDiscussion(@PathVariable("id") Long discussionId) {
        boolean success = discussionService.downvoteDiscussion(discussionId);
        if (success) {
            return new ResponseEntity<>("Discussion downvoted successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Discussion not found or already downvoted.", HttpStatus.NOT_FOUND);
    }
}

