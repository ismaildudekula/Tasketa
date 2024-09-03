package com.tasketa.service;


import com.tasketa.model.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Long issueId, Long userID, String content) throws Exception;

    void deleteComment(Long commentId, Long userId) throws Exception;

    List<Comment> getCommentsByIssueId(Long issueId) throws Exception;

}
