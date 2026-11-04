package com.moni.chatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PresenceService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String PRESENCE_KEY_PREFIX = "presence:";

    public void userConnected(String username) {
        redisTemplate.opsForValue().set(PRESENCE_KEY_PREFIX + username, "ONLINE", 5, TimeUnit.MINUTES);
    }

    public void userDisconnected(String username) {
        redisTemplate.delete(PRESENCE_KEY_PREFIX + username);
    }

    public boolean isUserOnline(String username) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(PRESENCE_KEY_PREFIX + username));
    }
}