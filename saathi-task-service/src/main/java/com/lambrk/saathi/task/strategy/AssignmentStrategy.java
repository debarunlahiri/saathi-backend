package com.lambrk.saathi.task.strategy;

import java.util.Optional;
import java.util.UUID;

public interface AssignmentStrategy {
  Optional<UUID> assignPartner(UUID taskId);
}
