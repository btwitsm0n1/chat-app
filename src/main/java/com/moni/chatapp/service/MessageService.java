package com.moni.chatapp.service;

import com.moni.chatapp.dto.ChatMessage;
import com.moni.chatapp.model.Message;
import com.moni.chatapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Message> getChatHistory(String user1, String user2, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return messageRepository.findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimestampAsc(
                user1, user2, user2, user1, pageable
        );
    }

    // Naya method Read Receipts ke liye
    public void updateMessageStatus(String messageId, String status) {
        messageRepository.findById(messageId).ifPresent(message -> {
            message.setStatus(status);
            messageRepository.save(message);
        });
    }
    // Group chat ki history nikalne ke liye
    public Page<Message> getGroupChatHistory(String roomId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return messageRepository.findByRoomIdOrderByTimestampAsc(roomId, pageable);
    }
}