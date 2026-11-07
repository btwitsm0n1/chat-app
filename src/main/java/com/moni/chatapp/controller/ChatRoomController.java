package com.moni.chatapp.controller;

import com.moni.chatapp.model.ChatRoom;
import com.moni.chatapp.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    // Naya Group/Room banane ke liye
    @PostMapping
    public ResponseEntity<ChatRoom> createRoom(@RequestBody ChatRoom chatRoom) {
        if(chatRoom.getType() == null) chatRoom.setType("GROUP");
        return ResponseEntity.ok(chatRoomRepository.save(chatRoom));
    }

    // Kis user ke kya rooms hain (Frontend ke sidebar ke liye)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatRoom>> getUserRooms(@PathVariable String userId) {
        return ResponseEntity.ok(chatRoomRepository.findByMemberIdsContaining(userId));
    }
}