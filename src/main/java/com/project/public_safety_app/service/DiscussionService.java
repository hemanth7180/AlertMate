package com.project.public_safety_app.service;

import com.project.public_safety_app.dto.CommentRequest;
import com.project.public_safety_app.dto.DiscussionDTO;
import com.project.public_safety_app.dto.UserDto;
import com.project.public_safety_app.model.Comment;
import com.project.public_safety_app.model.Discussion;
import com.project.public_safety_app.model.User;

import java.util.List;

public interface DiscussionService {
    Discussion createDiscussion(Discussion discussion,User user);

    List<Discussion> getAllDiscussions();

    List<Discussion> getDiscussionsByUser(String userName);

    void deleteDiscussionByName(String UserName,long id);

    Comment addComment(Long discussionId, CommentRequest commentRequest);

    List<Comment> getCommentsByDiscussionId(Long discussionId);

    boolean upvoteDiscussion(Long discussionId);

    boolean downvoteDiscussion(Long discussionId);
}
