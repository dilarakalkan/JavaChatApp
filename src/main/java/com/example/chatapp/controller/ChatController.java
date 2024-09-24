package com.example.chatapp.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        // Gelen mesajı al ve işleyerek geri dön
        return "Alındı: " + message;
    }
}
