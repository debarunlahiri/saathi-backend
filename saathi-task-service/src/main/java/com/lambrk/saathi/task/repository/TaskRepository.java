package com.lambrk.saathi.task.repository;

import com.lambrk.saathi.task.entity.Task;
import com.lambrk.saathi.task.enums.TaskStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, UUID> {
  List<Task> findByCustomerIdOrderByCreatedAtDesc(UUID customerId);

  List<Task> findByPartnerIdOrderByCreatedAtDesc(UUID partnerId);

  List<Task> findByTaskStatusOrderByCreatedAtDesc(TaskStatus taskStatus);

  long countByTaskStatusIn(List<TaskStatus> statuses);
}
