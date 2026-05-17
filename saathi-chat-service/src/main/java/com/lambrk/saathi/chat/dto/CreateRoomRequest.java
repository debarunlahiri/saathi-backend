package com.lambrk.saathi.chat.dto;

import jakarta.validation.constraints.NotNull;

public record CreateRoomRequest(
    @NotNull Long taskId, @NotNull Long customerId, @NotNull Long partnerId) {}
