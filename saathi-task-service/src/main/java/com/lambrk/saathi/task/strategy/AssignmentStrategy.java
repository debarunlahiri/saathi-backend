package com.lambrk.saathi.task.strategy;

import java.util.Optional;

public interface AssignmentStrategy {
  Optional<Long> assignPartner(Long taskId);
}
