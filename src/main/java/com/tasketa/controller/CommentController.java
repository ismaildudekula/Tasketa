package com.tasketa.controller;


import com.tasketa.model.Comment;
import com.tasketa.model.User;
import com.tasketa.request.CreateCommentRequest;
import com.tasketa.response.MessageResponse;
import com.tasketa.service.CommentService;
import com.tasketa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<Comment> createComment(
            @RequestBody CreateCommentRequest commentRequest,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);

        Comment createdComment = commentService.createComment(
                commentRequest.getIssueId(),
                user.getId(),
                commentRequest.getContent()
        );

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId, user.getId());
        MessageResponse response = new MessageResponse("Comment Deleted Successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId) throws Exception {
        List<Comment> comments = commentService.getCommentsByIssueId(issueId);
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }


}
