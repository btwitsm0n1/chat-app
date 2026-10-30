package com.moni.chatapp.repository;

import com.moni.chatapp.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    // Dono users ke beech ke messages nikalne ke liye
    List<Message> findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimestampAsc(
            String sender1, String receiver1, String sender2, String receiver2);
}