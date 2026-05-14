package com.lambrk.saathi.task.entity;

import com.lambrk.saathi.task.enums.TaskStatus;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "task_status_history")
public class TaskStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Enumerated(EnumType.STRING)
    @Column(name = "old_status")
    private TaskStatus oldStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "new_status", nullable = false)
    private TaskStatus newStatus;

    @Column(name = "changed_by")
    private Long changedBy;

    private String remarks;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void prePersist() {
        createdAt = Instant.now();
    }

    public void setTaskId(Long taskId) { this.taskId = taskId; }
    public void setOldStatus(TaskStatus oldStatus) { this.oldStatus = oldStatus; }
    public void setNewStatus(TaskStatus newStatus) { this.newStatus = newStatus; }
    public void setChangedBy(Long changedBy) { this.changedBy = changedBy; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
