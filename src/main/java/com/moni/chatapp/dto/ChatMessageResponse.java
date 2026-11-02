package com.moni.chatapp.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ChatMessageResponse {
    private String id;
    private String senderId;
    private String receiverId;
    private String content;
    private LocalDateTime timestamp;
}