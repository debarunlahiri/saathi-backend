package com.lambrk.saathi.task.dto.request;

import com.lambrk.saathi.task.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;

public record TaskStatusRequest(@NotNull TaskStatus status, Long changedBy, String remarks) {
}
