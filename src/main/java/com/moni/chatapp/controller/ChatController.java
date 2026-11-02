package com.moni.chatapp.controller;

import com.moni.chatapp.dto.ChatMessage;
import com.moni.chatapp.model.Message;
import com.moni.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageService messageService; // Repository ki jagah Service use kar rahe hain

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {

        // 1. Service ke through database mein save karo
        Message savedMessage = messageService.saveMessage(chatMessage);

        // 2. Receiver ko real-time message push karo
        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverId(),
                "/queue/messages",
                savedMessage
        );
    }
    // REST API to get chat history with pagination
    @org.springframework.web.bind.annotation.GetMapping("/api/chat/history")
    public org.springframework.http.ResponseEntity<org.springframework.data.domain.Page<Message>> getChatHistory(
            @org.springframework.web.bind.annotation.RequestParam String user1,
            @org.springframework.web.bind.annotation.RequestParam String user2,
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "0") int page,
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "20") int size) {

        return org.springframework.http.ResponseEntity.ok(messageService.getChatHistory(user1, user2, page, size));
    }
}