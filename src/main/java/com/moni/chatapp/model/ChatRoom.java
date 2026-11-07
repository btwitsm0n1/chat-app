package com.moni.chatapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "chat_rooms")
public class ChatRoom {
    @Id
    private String id;

    private String name; // Group ka naam
    private String type; // "PRIVATE" ya "GROUP"
    private List<String> memberIds; // Kaun kaun hai group mein
    private LocalDateTime createdAt = LocalDateTime.now();
}