package com.tasketa.service;


import com.tasketa.model.Chat;
import com.tasketa.model.Message;
import com.tasketa.model.User;
import com.tasketa.repository.MessageRepository;
import com.tasketa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectService projectService;

    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
        User user = userRepository.findById(senderId)
                .orElseThrow(() -> new Exception("User not found with id: " + senderId));

        Chat chat = projectService.getChatByProjectId(projectId);
        Message message = new Message();
        message.setSender(user);
        message.setChat(chat);
        message.setContent(content);
        Message savedMessage = messageRepository.save(message);

        chat.getMessages().add(savedMessage);
        return savedMessage;
    }

    @Override
    public List<Message> getMessagesByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);
        List<Message> messages = messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
        return messages;
    }
}
