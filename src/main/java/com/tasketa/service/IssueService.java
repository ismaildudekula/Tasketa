package com.tasketa.service;


import com.tasketa.model.Issue;
import com.tasketa.model.User;
import com.tasketa.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {


    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssuesByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issueRequest, User user) throws  Exception;

    void deleteIssue(Long issueId,Long userId) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;

}
