package com.lambrk.saathi.chat.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreateRoomRequest(
    @NotNull UUID taskId, @NotNull UUID customerId, @NotNull UUID partnerId) {}
