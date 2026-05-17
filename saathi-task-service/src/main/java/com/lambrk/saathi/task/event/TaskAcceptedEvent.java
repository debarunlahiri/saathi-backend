package com.lambrk.saathi.task.event;

import java.time.Instant;
import java.util.UUID;

public record TaskAcceptedEvent(UUID taskId, UUID partnerId, Instant occurredAt) {}
