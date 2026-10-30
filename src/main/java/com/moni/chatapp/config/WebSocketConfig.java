package com.moni.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Client in prefixes par messages sunega (subscribe karega)
        config.enableSimpleBroker("/topic", "/queue");

        // Client in prefixes par message bhejega
        config.setApplicationDestinationPrefixes("/app");

        // Private messages ke liye (jaise 1-to-1 chat)
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket connection URL: ws://localhost:8080/ws
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*") // Frontend connect kar sake isliye
                .withSockJS(); // Fallback agar browser direct WS support na kare
    }
}