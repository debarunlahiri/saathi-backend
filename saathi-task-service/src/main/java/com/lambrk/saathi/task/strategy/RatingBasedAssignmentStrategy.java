package com.lambrk.saathi.task.strategy;

import com.lambrk.saathi.task.client.PartnerClient;
import com.lambrk.saathi.task.entity.Task;
import com.lambrk.saathi.task.enums.TaskStatus;
import com.lambrk.saathi.task.repository.TaskRepository;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class RatingBasedAssignmentStrategy implements AssignmentStrategy {
  private final TaskRepository taskRepository;
  private final PartnerClient partnerClient;

  public RatingBasedAssignmentStrategy(TaskRepository taskRepository, PartnerClient partnerClient) {
    this.taskRepository = taskRepository;
    this.partnerClient = partnerClient;
  }

  @Override
  public Optional<Long> assignPartner(Long taskId) {
    Task task = taskRepository.findById(taskId).orElseThrow();
    if (task.getTaskStatus() != TaskStatus.SEARCHING_PARTNER) {
      return Optional.empty();
    }
    return PartnerSelectionSupport.highestRated(task, partnerClient.availablePartners());
  }
}
