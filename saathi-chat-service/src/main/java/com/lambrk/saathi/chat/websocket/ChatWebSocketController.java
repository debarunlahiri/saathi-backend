package com.lambrk.saathi.chat.websocket;

import com.lambrk.saathi.chat.document.ChatMessage;
import com.lambrk.saathi.chat.dto.ChatSendRequest;
import com.lambrk.saathi.chat.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatWebSocketController(ChatService chatService, SimpMessagingTemplate messagingTemplate) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.send")
    public void send(ChatSendRequest request) {
        ChatMessage message = chatService.send(request);
        messagingTemplate.convertAndSend("/topic/chat/" + request.roomId(), message);
        messagingTemplate.convertAndSendToUser(request.senderId().toString(), "/queue/messages", message);
    }

    @MessageMapping("/chat.typing")
    public void typing(ChatSendRequest request) {
        messagingTemplate.convertAndSend("/topic/chat/" + request.roomId() + "/typing", request);
    }

    @MessageMapping("/chat.read")
    public void read(ChatSendRequest request) {
        messagingTemplate.convertAndSend("/topic/chat/" + request.roomId() + "/read", request);
    }
}
