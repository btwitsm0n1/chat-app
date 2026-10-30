package com.moni.chatapp.controller;

import com.moni.chatapp.dto.ChatMessage;
import com.moni.chatapp.model.Message;
import com.moni.chatapp.repository.MessageRepository;
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
    private MessageRepository messageRepository;

    // Client yahan message bhejega: /app/chat
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {

        // 1. Database mein save karo
        Message message = new Message();
        message.setSenderId(chatMessage.getSenderId());
        message.setReceiverId(chatMessage.getReceiverId());
        message.setContent(chatMessage.getContent());
        messageRepository.save(message);

        // 2. Receiver ko real-time message push karo
        // Receiver is URL par subscribe karega: /user/{receiverId}/queue/messages
        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverId(),
                "/queue/messages",
                message
        );
    }
}