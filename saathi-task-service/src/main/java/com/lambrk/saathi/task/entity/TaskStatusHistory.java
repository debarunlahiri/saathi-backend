package com.lambrk.saathi.task.entity;

import com.lambrk.saathi.task.enums.TaskStatus;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "task_status_history")
public class TaskStatusHistory {
  @Id
  @Column(columnDefinition = "uuid", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "task_id", nullable = false, columnDefinition = "uuid")
  private UUID taskId;

  @Enumerated(EnumType.STRING)
  @Column(name = "old_status")
  private TaskStatus oldStatus;

  @Enumerated(EnumType.STRING)
  @Column(name = "new_status", nullable = false)
  private TaskStatus newStatus;

  @Column(name = "changed_by", columnDefinition = "uuid")
  private UUID changedBy;

  private String remarks;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @PrePersist
  void prePersist() {
    generateId();
    createdAt = Instant.now();
  }

  void generateId() {
    if (id == null) {
      id = com.github.f4b6a3.uuid.UuidCreator.getTimeOrderedEpoch();
    }
  }

  public void setTaskId(UUID taskId) {
    this.taskId = taskId;
  }

  public void setOldStatus(TaskStatus oldStatus) {
    this.oldStatus = oldStatus;
  }

  public void setNewStatus(TaskStatus newStatus) {
    this.newStatus = newStatus;
  }

  public void setChangedBy(UUID changedBy) {
    this.changedBy = changedBy;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }
}
