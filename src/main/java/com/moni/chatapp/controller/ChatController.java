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
}