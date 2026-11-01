package com.moni.chatapp.service;

import com.moni.chatapp.dto.ChatMessage;
import com.moni.chatapp.model.Message;
import com.moni.chatapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(ChatMessage chatMessage) {
        Message message = new Message();
        message.setSenderId(chatMessage.getSenderId());
        message.setReceiverId(chatMessage.getReceiverId());
        message.setContent(chatMessage.getContent());
        return messageRepository.save(message);
    }
}