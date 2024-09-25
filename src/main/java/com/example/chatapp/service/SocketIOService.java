package com.example.chatapp.service;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.stereotype.Service;


@Service
public class SocketIOService {

    private final SocketIOServer socketIOServer;

    public SocketIOService(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
    }

    @OnConnect
    public void onConnect() {
        System.out.println("Client connected");
    }

    @OnDisconnect
    public void onDisconnect() {
        System.out.println("Client disconnected");
    }

    @OnEvent("message")
    public void onMessage(String message) {
        System.out.println("Message received: " + message);
        // Mesajı tüm bağlı kullanıcılara gönder
        socketIOServer.getBroadcastOperations().sendEvent("message", message);
    }
}


