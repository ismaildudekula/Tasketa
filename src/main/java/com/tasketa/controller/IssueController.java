package com.tasketa.controller;


import com.tasketa.DTO.IssueDTO;
import com.tasketa.model.Issue;
import com.tasketa.model.User;
import com.tasketa.request.IssueRequest;
import com.tasketa.response.MessageResponse;
import com.tasketa.service.IssueService;
import com.tasketa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {


    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;


    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
        Issue issue = issueService.getIssueById(issueId);
        return new ResponseEntity<>(issue, HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssuesById(@PathVariable Long projectId) throws Exception {
        List<Issue> issues = issueService.getIssuesByProjectId(projectId);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<IssueDTO> createIssue(
            @RequestBody IssueRequest issueRequest,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {
        User tokenUser = userService.findUserProfileByJwt(jwt);

        Issue createdIssue = issueService.createIssue(issueRequest,tokenUser);
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setDescription(createdIssue.getDescription());
        issueDTO.setTags(createdIssue.getTags());
        issueDTO.setProject(createdIssue.getProject());
        issueDTO.setPriority(createdIssue.getPriority());
        issueDTO.setStatus(createdIssue.getStatus());
        issueDTO.setTitle(createdIssue.getTitle());
        issueDTO.setDueDate(createdIssue.getDueDate());
        issueDTO.setProjectId(createdIssue.getProjectId());
        issueDTO.setId(createdIssue.getId());
        issueDTO.setAssignee(createdIssue.getAssignee());

        return ResponseEntity.ok(issueDTO);
    }


    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(
            @PathVariable Long issueId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        issueService.deleteIssue(issueId, user.getId());
        MessageResponse messageResponse = new MessageResponse("Issue Deleted Successfully");
        return ResponseEntity.ok(messageResponse);
    }


    @PostMapping("/{issueId}/assignee/{assigneeId}")
    public ResponseEntity<Issue> addUserTOIssue(
            @PathVariable Long issueId,
            @PathVariable Long assigneeId
    ) throws Exception {

        Issue issue = issueService.addUserToIssue(issueId, assigneeId);
        return ResponseEntity.ok(issue);

    }


    @PostMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateStatus(
            @PathVariable Long issueId,
            @PathVariable String status
    ) throws Exception {
        Issue issue = issueService.updateStatus(issueId, status);
        return ResponseEntity.ok(issue);
    }




}
