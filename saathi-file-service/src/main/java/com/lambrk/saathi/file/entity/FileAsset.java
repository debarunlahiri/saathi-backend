package com.lambrk.saathi.file.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "file_assets")
public class FileAsset {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "owner_user_id")
  private Long ownerUserId;

  @Column(name = "reference_type")
  private String referenceType;

  @Column(name = "reference_id")
  private String referenceId;

  @Column(name = "file_name", nullable = false)
  private String fileName;

  @Column(name = "content_type")
  private String contentType;

  @Column(name = "storage_url", nullable = false)
  private String storageUrl;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @PrePersist
  void prePersist() {
    createdAt = Instant.now();
  }

  public Long getId() {
    return id;
  }

  public Long getOwnerUserId() {
    return ownerUserId;
  }

  public void setOwnerUserId(Long ownerUserId) {
    this.ownerUserId = ownerUserId;
  }

  public String getReferenceType() {
    return referenceType;
  }

  public void setReferenceType(String referenceType) {
    this.referenceType = referenceType;
  }

  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getStorageUrl() {
    return storageUrl;
  }

  public void setStorageUrl(String storageUrl) {
    this.storageUrl = storageUrl;
  }
}
