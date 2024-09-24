package com.example.chatapp.service;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final SocketIOServer server;

    public MessageListener(SocketIOServer server) {

        this.server = server;
    }

    @OnEvent("message")
    public void onMessageReceived(String message) {
        System.out.println("Message received: " + message);
        // Tüm kullanıcılara mesajı yay.
        server.getBroadcastOperations().sendEvent("message", message);
    }
}