package com.example.chatapp.controller;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.chatapp.model.ChatMessage; // ChatMessage import edildi
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SocketIOController {

    @Autowired
    private SocketIOServer socketServer;

    public SocketIOController(SocketIOServer socketServer) {
        this.socketServer = socketServer;

        this.socketServer.addConnectListener(onUserConnectWithSocket);
        this.socketServer.addDisconnectListener(onUserDisconnectWithSocket);
        this.socketServer.addEventListener("messageSendToUser", ChatMessage.class, onSendMessage); // Güncellendi
    }

    private final ConnectListener onUserConnectWithSocket = client -> {
        log.info("User connected: " + client.getSessionId());
    };

    private final DisconnectListener onUserDisconnectWithSocket = client -> {
        log.info("User disconnected: " + client.getSessionId());
    };

    private final DataListener<ChatMessage> onSendMessage = (client, chatMessage, ackRequest) -> { // Güncellendi
        log.info("Received message: " + chatMessage.getContent());
        // Mesajı diğer istemcilere gönderin veya işleyin
        socketServer.getBroadcastOperations().sendEvent("messageSendToUser", chatMessage);
    };
}
