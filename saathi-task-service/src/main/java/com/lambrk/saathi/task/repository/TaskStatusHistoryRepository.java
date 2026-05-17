package com.lambrk.saathi.task.repository;

import com.lambrk.saathi.task.entity.TaskStatusHistory;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusHistoryRepository extends JpaRepository<TaskStatusHistory, UUID> {}
