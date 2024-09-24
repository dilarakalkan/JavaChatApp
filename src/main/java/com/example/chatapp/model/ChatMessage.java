package com.example.chatapp.model;

import lombok.*;
import java.time.LocalDateTime;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChatMessage {

    private MessageType type;
    private String content;
    private String sender;
    private String receiver;  //
    private LocalDateTime timestamp;

    // Varsayılan olarak, mesaj oluşturulduğunda zaman damgasını şu anki zamana ayarlar
    public ChatMessage(MessageType type, String content, String sender, String receiver) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = LocalDateTime.now();
    }
}
