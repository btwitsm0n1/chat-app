package com.moni.chatapp.repository;

import com.moni.chatapp.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {

    // Pagination ke saath messages fetch karne ke liye
    Page<Message> findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimestampAsc(
            String sender1, String receiver1, String sender2, String receiver2, Pageable pageable);
}