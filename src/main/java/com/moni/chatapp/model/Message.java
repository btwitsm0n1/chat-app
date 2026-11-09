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
    private String receiverId; // Private chat ke liye (null ho sakta hai group mein)
    private String roomId;     // Group chat ke liye (null ho sakta hai private mein)
    private String content;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String status = "SENT";
}