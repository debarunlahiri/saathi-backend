package com.lambrk.saathi.chat.service;

import com.lambrk.saathi.chat.document.ChatMessage;
import com.lambrk.saathi.chat.document.ChatRoom;
import com.lambrk.saathi.chat.dto.ChatSendRequest;
import com.lambrk.saathi.chat.dto.CreateRoomRequest;
import com.lambrk.saathi.chat.enums.MessageStatus;
import com.lambrk.saathi.chat.repository.ChatMessageRepository;
import com.lambrk.saathi.chat.repository.ChatRoomRepository;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
  private final ChatRoomRepository roomRepository;
  private final ChatMessageRepository messageRepository;

  public ChatService(ChatRoomRepository roomRepository, ChatMessageRepository messageRepository) {
    this.roomRepository = roomRepository;
    this.messageRepository = messageRepository;
  }

  public ChatRoom createRoom(CreateRoomRequest request) {
    return roomRepository
        .findByTaskId(request.taskId())
        .orElseGet(
            () -> {
              ChatRoom room = new ChatRoom();
              room.setId("TASK_" + request.taskId());
              room.setTaskId(request.taskId());
              room.setCustomerId(request.customerId());
              room.setPartnerId(request.partnerId());
              room.setStatus("ACTIVE");
              room.setCreatedAt(Instant.now());
              return roomRepository.save(room);
            });
  }

  public ChatRoom room(Long taskId) {
    return roomRepository.findByTaskId(taskId).orElseThrow();
  }

  public List<ChatMessage> messages(String roomId) {
    return messageRepository.findByRoomIdOrderBySentAtAsc(roomId);
  }

  public ChatMessage send(ChatSendRequest request) {
    ChatMessage message = new ChatMessage();
    message.setRoomId(request.roomId());
    message.setTaskId(request.taskId());
    message.setSenderId(request.senderId());
    message.setSenderRole(request.senderRole());
    message.setMessageType(request.messageType());
    message.setContent(request.content());
    message.setAttachmentUrl(request.attachmentUrl());
    message.setStatus(MessageStatus.SENT);
    message.setSentAt(Instant.now());
    return messageRepository.save(message);
  }

  public List<ChatMessage> markAsDelivered(String roomId, Long userId) {
    List<ChatMessage> messages =
        messageRepository.findByRoomIdAndSenderIdNotAndDeliveredAtIsNull(roomId, userId);
    messages.forEach(
        msg -> {
          msg.setStatus(MessageStatus.DELIVERED);
          msg.setDeliveredAt(Instant.now());
        });
    return messageRepository.saveAll(messages);
  }

  public ChatMessage markAsRead(String messageId) {
    ChatMessage message = messageRepository.findById(messageId).orElseThrow();
    message.setStatus(MessageStatus.READ);
    message.setReadAt(Instant.now());
    return messageRepository.save(message);
  }

  public List<ChatMessage> messagesByRoom(String roomId, int page, int size) {
    return messageRepository.findByRoomIdOrderBySentAtDesc(
        roomId, org.springframework.data.domain.PageRequest.of(page, size));
  }
}
