package com.tasketa.controller;


import com.tasketa.model.Chat;
import com.tasketa.model.Message;
import com.tasketa.model.User;
import com.tasketa.request.CreateMessageRequest;
import com.tasketa.service.MessageService;
import com.tasketa.service.ProjectService;
import com.tasketa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;


    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(
            @RequestBody CreateMessageRequest request
            ) throws Exception {
        User user = userService.findUserById(request.getSenderId());
        Chat chat = projectService.getChatByProjectId(request.getProjectId());

        Message message = messageService.sendMessage(request.getSenderId(), request.getProjectId(), request.getContent());
        return ResponseEntity.ok(message);

    }


    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByProjectId(
            @PathVariable Long projectId
    ) throws Exception {
        List<Message> messages = messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }

}
