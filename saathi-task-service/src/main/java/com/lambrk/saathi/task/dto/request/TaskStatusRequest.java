package com.lambrk.saathi.task.dto.request;

import com.lambrk.saathi.task.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record TaskStatusRequest(@NotNull TaskStatus status, UUID changedBy, String remarks) {}
