package com.lambrk.saathi.chat.repository;

import com.lambrk.saathi.chat.document.ChatRoom;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
  Optional<ChatRoom> findByTaskId(Long taskId);
}
