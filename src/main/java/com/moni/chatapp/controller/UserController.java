package com.moni.chatapp.controller;

import com.moni.chatapp.model.User;
import com.moni.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private com.moni.chatapp.service.PresenceService presenceService;

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String query) {
        return ResponseEntity.ok(userRepository.findByUsernameContainingIgnoreCase(query));
    }
    // Kisi user ka online/offline status check karne ke liye API
    @GetMapping("/{username}/status")
    public ResponseEntity<java.util.Map<String, Object>> getUserStatus(@PathVariable String username) {
        boolean isOnline = presenceService.isUserOnline(username);
        return ResponseEntity.ok(java.util.Map.of("username", username, "isOnline", isOnline));
    }

}