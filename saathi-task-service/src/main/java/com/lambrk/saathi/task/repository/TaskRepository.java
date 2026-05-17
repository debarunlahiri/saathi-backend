package com.lambrk.saathi.task.repository;

import com.lambrk.saathi.task.entity.Task;
import com.lambrk.saathi.task.enums.TaskStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

  List<Task> findByPartnerIdOrderByCreatedAtDesc(Long partnerId);

  List<Task> findByTaskStatusOrderByCreatedAtDesc(TaskStatus taskStatus);

  long countByTaskStatusIn(List<TaskStatus> statuses);
}
