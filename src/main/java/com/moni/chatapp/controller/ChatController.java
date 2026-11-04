package com.moni.chatapp.controller;

import com.moni.chatapp.dto.ChatMessage;
import com.moni.chatapp.model.Message;
import com.moni.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        Message savedMessage = messageService.saveMessage(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverId(),
                "/queue/messages",
                savedMessage
        );
    }

    @GetMapping("/api/chat/history")
    public ResponseEntity<Page<Message>> getChatHistory(
            @RequestParam String user1,
            @RequestParam String user2,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        return ResponseEntity.ok(messageService.getChatHistory(user1, user2, page, size));
    }

    // Naya Endpoint Read Receipts ke liye
    @MessageMapping("/chat.seen")
    public void markAsSeen(@Payload Map<String, String> payload) {
        String messageId = payload.get("messageId");
        String senderId = payload.get("senderId");

        messageService.updateMessageStatus(messageId, "SEEN");

        messagingTemplate.convertAndSendToUser(
                senderId,
                "/queue/read-receipts",
                payload
        );
    }
}