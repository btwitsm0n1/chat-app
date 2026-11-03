package com.moni.chatapp.repository;

import com.moni.chatapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    // Naya method User Search ke liye
    List<User> findByUsernameContainingIgnoreCase(String username);
}