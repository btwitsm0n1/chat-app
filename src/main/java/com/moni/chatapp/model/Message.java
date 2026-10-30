package com.moni.chatapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "messages")
public class Message {
    @Id
    private String id;

    private String senderId;
    private String receiverId; // 1-to-1 ke liye
    private String roomId;     // Group chat ke liye
    private String content;
    private LocalDateTime timestamp = LocalDateTime.now();
}