package com.lambrk.saathi.chat.dto;

import com.lambrk.saathi.chat.enums.MessageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChatSendRequest(
    @NotBlank String roomId,
    @NotNull Long taskId,
    @NotNull Long senderId,
    @NotBlank String senderRole,
    @NotNull MessageType messageType,
    String content,
    String attachmentUrl) {}
