package com.lambrk.saathi.task.event;

import java.time.Instant;

public record TaskCreatedEvent(Long taskId, Long customerId, Instant occurredAt) {
}
