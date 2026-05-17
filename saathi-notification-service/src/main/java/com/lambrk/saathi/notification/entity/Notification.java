package com.lambrk.saathi.notification.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(
    name = "notifications",
    indexes = @Index(name = "idx_notification_user", columnList = "user_id"))
public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, length = 1000)
  private String message;

  @Column(name = "notification_type")
  private String notificationType;

  @Column(name = "reference_id")
  private String referenceId;

  @Column(name = "read_status", nullable = false)
  private boolean readStatus;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @PrePersist
  void prePersist() {
    createdAt = Instant.now();
  }

  public Long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }

  public boolean isReadStatus() {
    return readStatus;
  }

  public void setReadStatus(boolean readStatus) {
    this.readStatus = readStatus;
  }
}
