package com.lambrk.saathi.chat.repository;

import com.lambrk.saathi.chat.document.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findByTaskId(Long taskId);
}
