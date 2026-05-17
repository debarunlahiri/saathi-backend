package com.lambrk.saathi.wallet.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "partner_payouts")
public class PartnerPayout {
  @Id
  @Column(columnDefinition = "uuid", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "partner_id", nullable = false)
  private UUID partnerId;

  @Column(name = "amount", precision = 12, scale = 2, nullable = false)
  private BigDecimal amount;

  @Column(name = "payout_method", nullable = false)
  private String payoutMethod;

  @Column(name = "payout_reference")
  private String payoutReference;

  @Column(name = "status", nullable = false)
  private String status;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @PrePersist
  void generateId() {
    if (id == null) {
      id = com.github.f4b6a3.uuid.UuidCreator.getTimeOrderedEpoch();
    }
    createdAt = Instant.now();
    if (status == null) status = "INITIATED";
  }

  public UUID getId() {
    return id;
  }

  public UUID getPartnerId() {
    return partnerId;
  }

  public void setPartnerId(UUID partnerId) {
    this.partnerId = partnerId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getPayoutMethod() {
    return payoutMethod;
  }

  public void setPayoutMethod(String payoutMethod) {
    this.payoutMethod = payoutMethod;
  }

  public String getPayoutReference() {
    return payoutReference;
  }

  public void setPayoutReference(String payoutReference) {
    this.payoutReference = payoutReference;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }
}
