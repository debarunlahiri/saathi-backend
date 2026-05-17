package com.lambrk.saathi.task.event;

import java.time.Instant;
import java.util.UUID;

public record TaskCreatedEvent(UUID taskId, UUID customerId, Instant occurredAt) {}
