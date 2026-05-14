package com.lambrk.saathi.task.event;

import java.time.Instant;

public record TaskAcceptedEvent(Long taskId, Long partnerId, Instant occurredAt) {
}
