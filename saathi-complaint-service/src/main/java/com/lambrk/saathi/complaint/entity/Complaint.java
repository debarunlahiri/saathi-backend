package com.lambrk.saathi.complaint.entity;

import com.lambrk.saathi.complaint.enums.ComplaintStatus;
import com.lambrk.saathi.complaint.enums.ComplaintType;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "complaints")
public class Complaint {
  @Id
  @Column(columnDefinition = "uuid", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "task_id")
  private UUID taskId;

  @Column(name = "raised_by", nullable = false)
  private UUID raisedBy;

  @Enumerated(EnumType.STRING)
  @Column(name = "complaint_type", nullable = false)
  private ComplaintType complaintType;

  @Column(nullable = false, length = 2000)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ComplaintStatus status = ComplaintStatus.OPEN;

  @Column(name = "admin_remarks", length = 2000)
  private String adminRemarks;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  @PrePersist
  void generateId() {
    if (id == null) {
      id = com.github.f4b6a3.uuid.UuidCreator.getTimeOrderedEpoch();
    }
    createdAt = Instant.now();
    updatedAt = createdAt;
  }

  @PreUpdate
  void preUpdate() {
    updatedAt = Instant.now();
  }

  public UUID getId() {
    return id;
  }

  public UUID getTaskId() {
    return taskId;
  }

  public void setTaskId(UUID taskId) {
    this.taskId = taskId;
  }

  public UUID getRaisedBy() {
    return raisedBy;
  }

  public void setRaisedBy(UUID raisedBy) {
    this.raisedBy = raisedBy;
  }

  public ComplaintType getComplaintType() {
    return complaintType;
  }

  public void setComplaintType(ComplaintType complaintType) {
    this.complaintType = complaintType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ComplaintStatus getStatus() {
    return status;
  }

  public void setStatus(ComplaintStatus status) {
    this.status = status;
  }

  public String getAdminRemarks() {
    return adminRemarks;
  }

  public void setAdminRemarks(String adminRemarks) {
    this.adminRemarks = adminRemarks;
  }
}
