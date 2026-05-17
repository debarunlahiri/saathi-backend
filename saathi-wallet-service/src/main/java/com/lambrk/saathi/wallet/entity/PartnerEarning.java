package com.lambrk.saathi.wallet.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "partner_earnings")
public class PartnerEarning {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "partner_id", nullable = false)
  private Long partnerId;

  @Column(name = "task_id", nullable = false)
  private Long taskId;

  @Column(name = "gross_amount", nullable = false, precision = 12, scale = 2)
  private BigDecimal grossAmount;

  @Column(name = "platform_commission", nullable = false, precision = 12, scale = 2)
  private BigDecimal platformCommission;

  @Column(name = "net_amount", nullable = false, precision = 12, scale = 2)
  private BigDecimal netAmount;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @PrePersist
  void prePersist() {
    createdAt = Instant.now();
  }

  public Long getId() {
    return id;
  }

  public Long getPartnerId() {
    return partnerId;
  }

  public void setPartnerId(Long partnerId) {
    this.partnerId = partnerId;
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }

  public BigDecimal getGrossAmount() {
    return grossAmount;
  }

  public void setGrossAmount(BigDecimal grossAmount) {
    this.grossAmount = grossAmount;
  }

  public BigDecimal getPlatformCommission() {
    return platformCommission;
  }

  public void setPlatformCommission(BigDecimal platformCommission) {
    this.platformCommission = platformCommission;
  }

  public BigDecimal getNetAmount() {
    return netAmount;
  }

  public void setNetAmount(BigDecimal netAmount) {
    this.netAmount = netAmount;
  }
}
