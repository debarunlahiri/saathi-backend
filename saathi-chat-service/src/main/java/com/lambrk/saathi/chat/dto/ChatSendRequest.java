package com.lambrk.saathi.chat.dto;

import com.lambrk.saathi.chat.enums.MessageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ChatSendRequest(
    @NotBlank String roomId,
    @NotNull UUID taskId,
    @NotNull UUID senderId,
    @NotBlank String senderRole,
    @NotNull MessageType messageType,
    String content,
    String attachmentUrl) {}
