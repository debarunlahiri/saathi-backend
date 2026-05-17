package com.lambrk.saathi.chat.controller;

import com.lambrk.saathi.chat.document.ChatMessage;
import com.lambrk.saathi.chat.document.ChatRoom;
import com.lambrk.saathi.chat.dto.ApiResponse;
import com.lambrk.saathi.chat.dto.CreateRoomRequest;
import com.lambrk.saathi.chat.service.ChatService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
  private final ChatService chatService;

  public ChatController(ChatService chatService) {
    this.chatService = chatService;
  }

  @PostMapping("/rooms")
  public ApiResponse<ChatRoom> createRoom(@Valid @RequestBody CreateRoomRequest request) {
    return ApiResponse.success("Chat room created successfully", chatService.createRoom(request));
  }

  @GetMapping("/rooms/{taskId}")
  public ApiResponse<ChatRoom> room(@PathVariable Long taskId) {
    return ApiResponse.success("Chat room fetched successfully", chatService.room(taskId));
  }

  @GetMapping("/messages/{roomId}")
  public ApiResponse<List<ChatMessage>> messages(@PathVariable String roomId) {
    return ApiResponse.success("Chat messages fetched successfully", chatService.messages(roomId));
  }
}
