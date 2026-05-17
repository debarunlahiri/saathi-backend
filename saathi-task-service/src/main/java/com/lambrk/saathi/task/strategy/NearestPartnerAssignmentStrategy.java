package com.lambrk.saathi.task.strategy;

import com.lambrk.saathi.task.client.PartnerClient;
import com.lambrk.saathi.task.entity.Task;
import com.lambrk.saathi.task.enums.TaskStatus;
import com.lambrk.saathi.task.repository.TaskRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class NearestPartnerAssignmentStrategy implements AssignmentStrategy {
  private final TaskRepository taskRepository;
  private final PartnerClient partnerClient;

  public NearestPartnerAssignmentStrategy(
      TaskRepository taskRepository, PartnerClient partnerClient) {
    this.taskRepository = taskRepository;
    this.partnerClient = partnerClient;
  }

  @Override
  public Optional<UUID> assignPartner(UUID taskId) {
    Task task = taskRepository.findById(taskId).orElseThrow();
    if (task.getTaskStatus() != TaskStatus.SEARCHING_PARTNER) {
      return Optional.empty();
    }
    return PartnerSelectionSupport.nearest(task, partnerClient.availablePartners());
  }
}
