package com.lambrk.saathi.task.event;

import java.time.Instant;

public record TaskCompletedEvent(Long taskId, Long partnerId, Instant occurredAt) {
}
