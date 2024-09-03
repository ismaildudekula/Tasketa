package com.tasketa.service;


import com.tasketa.model.Comment;
import com.tasketa.model.Issue;
import com.tasketa.model.User;
import com.tasketa.repository.CommentRepository;
import com.tasketa.repository.IssueRepository;
import com.tasketa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class CommentServiceImpl implements CommentService{


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment createComment(Long issueId, Long userID, String content) throws Exception {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        Optional<User> userOptional = userRepository.findById(userID);

        if(issueOptional.isEmpty()){
            throw new Exception("Cannot create comment - Invalid Issue Id: " + issueId);
        }

        if(userOptional.isEmpty()){
            throw new Exception("Cannot create comment - Invalid User Id: " + userID );
        }

        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comment comment = new Comment();
        comment.setIssue(issue);
        comment.setUser(user);
        comment.setContent(content);
        comment.setDateTime(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        issue.getComments().add(savedComment);

        return savedComment;

    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(commentOptional.isEmpty()){
            throw new Exception("Cannot delete comment - invalid comment");
        }

        if(userOptional.isEmpty()){
            throw new Exception("Cannot delete comment - invalid user");
        }

        Comment comment = commentOptional.get();
        User user = userOptional.get();

        if(comment.getUser().equals(user)){
            commentRepository.delete(comment);
        }
        else{
            throw new Exception("Cannot delete user - Invalid owner of comment");
        }

    }

    @Override
    public List<Comment> getCommentsByIssueId(Long issueId) throws Exception {
        return commentRepository.findByIssueId(issueId);
    }
}
