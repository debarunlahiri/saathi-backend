package com.lambrk.saathi.chat.repository;

import com.lambrk.saathi.chat.document.ChatMessage;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
  List<ChatMessage> findByRoomIdOrderBySentAtAsc(String roomId);

  List<ChatMessage> findByRoomIdAndSenderIdNotAndDeliveredAtIsNull(String roomId, Long senderId);

  List<ChatMessage> findByRoomIdOrderBySentAtDesc(String roomId, Pageable pageable);
}
