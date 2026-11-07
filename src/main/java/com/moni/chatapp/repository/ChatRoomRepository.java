package com.moni.chatapp.repository;

import com.moni.chatapp.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    // Kisi user ke saare groups/rooms nikalne ke liye
    List<ChatRoom> findByMemberIdsContaining(String userId);
}