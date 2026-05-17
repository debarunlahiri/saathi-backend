package com.lambrk.saathi.task.entity;

import com.lambrk.saathi.task.enums.PaymentStatus;
import com.lambrk.saathi.task.enums.TaskStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "tasks",
    indexes = {
      @Index(name = "idx_tasks_customer", columnList = "customer_id"),
      @Index(name = "idx_tasks_partner", columnList = "partner_id"),
      @Index(name = "idx_tasks_status", columnList = "task_status")
    })
public class Task {
  @Id
  @Column(columnDefinition = "uuid", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "customer_id", nullable = false, columnDefinition = "uuid")
  private UUID customerId;

  @Column(name = "partner_id", columnDefinition = "uuid")
  private UUID partnerId;

  @Column(name = "service_category_id", nullable = false, columnDefinition = "uuid")
  private UUID serviceCategoryId;

  @Column(nullable = false)
  private String title;

  @Column(length = 2000)
  private String description;

  @Column(name = "pickup_address", nullable = false)
  private String pickupAddress;

  @Column(name = "pickup_latitude", precision = 10, scale = 7)
  private BigDecimal pickupLatitude;

  @Column(name = "pickup_longitude", precision = 10, scale = 7)
  private BigDecimal pickupLongitude;

  @Column(name = "drop_address")
  private String dropAddress;

  @Column(name = "task_date_time")
  private OffsetDateTime taskDateTime;

  @Column(name = "estimated_price", precision = 10, scale = 2)
  private BigDecimal estimatedPrice = BigDecimal.ZERO;

  @Column(name = "final_price", precision = 10, scale = 2)
  private BigDecimal finalPrice;

  @Enumerated(EnumType.STRING)
  @Column(name = "task_status", nullable = false)
  private TaskStatus taskStatus = TaskStatus.CREATED;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_status", nullable = false)
  private PaymentStatus paymentStatus = PaymentStatus.PENDING;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  @PrePersist
  void prePersist() {
    generateId();
    createdAt = Instant.now();
    updatedAt = createdAt;
  }

  void generateId() {
    if (id == null) {
      id = com.github.f4b6a3.uuid.UuidCreator.getTimeOrderedEpoch();
    }
  }

  @PreUpdate
  void preUpdate() {
    updatedAt = Instant.now();
  }

  public UUID getId() {
    return id;
  }

  public UUID getCustomerId() {
    return customerId;
  }

  public void setCustomerId(UUID customerId) {
    this.customerId = customerId;
  }

  public UUID getPartnerId() {
    return partnerId;
  }

  public void setPartnerId(UUID partnerId) {
    this.partnerId = partnerId;
  }

  public UUID getServiceCategoryId() {
    return serviceCategoryId;
  }

  public void setServiceCategoryId(UUID serviceCategoryId) {
    this.serviceCategoryId = serviceCategoryId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPickupAddress() {
    return pickupAddress;
  }

  public void setPickupAddress(String pickupAddress) {
    this.pickupAddress = pickupAddress;
  }

  public BigDecimal getPickupLatitude() {
    return pickupLatitude;
  }

  public void setPickupLatitude(BigDecimal pickupLatitude) {
    this.pickupLatitude = pickupLatitude;
  }

  public BigDecimal getPickupLongitude() {
    return pickupLongitude;
  }

  public void setPickupLongitude(BigDecimal pickupLongitude) {
    this.pickupLongitude = pickupLongitude;
  }

  public String getDropAddress() {
    return dropAddress;
  }

  public void setDropAddress(String dropAddress) {
    this.dropAddress = dropAddress;
  }

  public OffsetDateTime getTaskDateTime() {
    return taskDateTime;
  }

  public void setTaskDateTime(OffsetDateTime taskDateTime) {
    this.taskDateTime = taskDateTime;
  }

  public BigDecimal getEstimatedPrice() {
    return estimatedPrice;
  }

  public void setEstimatedPrice(BigDecimal estimatedPrice) {
    this.estimatedPrice = estimatedPrice;
  }

  public BigDecimal getFinalPrice() {
    return finalPrice;
  }

  public void setFinalPrice(BigDecimal finalPrice) {
    this.finalPrice = finalPrice;
  }

  public TaskStatus getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(TaskStatus taskStatus) {
    this.taskStatus = taskStatus;
  }

  public PaymentStatus getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(PaymentStatus paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }
}
