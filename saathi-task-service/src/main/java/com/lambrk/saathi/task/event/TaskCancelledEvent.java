package com.lambrk.saathi.task.event;

import java.time.Instant;

public record TaskCancelledEvent(Long taskId, String reason, Instant occurredAt) {
}
