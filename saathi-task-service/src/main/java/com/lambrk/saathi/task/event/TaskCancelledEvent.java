package com.lambrk.saathi.task.event;

import java.time.Instant;
import java.util.UUID;

public record TaskCancelledEvent(UUID taskId, String reason, Instant occurredAt) {}
