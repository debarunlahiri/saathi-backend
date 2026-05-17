package com.lambrk.saathi.wallet.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(
    name = "partner_wallets",
    indexes = @Index(name = "idx_wallet_partner", columnList = "partner_id", unique = true))
public class PartnerWallet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "partner_id", nullable = false, unique = true)
  private Long partnerId;

  @Column(name = "available_balance", precision = 12, scale = 2)
  private BigDecimal availableBalance = BigDecimal.ZERO;

  @Column(name = "pending_balance", precision = 12, scale = 2)
  private BigDecimal pendingBalance = BigDecimal.ZERO;

  @Column(name = "total_earnings", precision = 12, scale = 2)
  private BigDecimal totalEarnings = BigDecimal.ZERO;

  @Column(name = "total_withdrawn", precision = 12, scale = 2)
  private BigDecimal totalWithdrawn = BigDecimal.ZERO;

  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  @PrePersist
  @PreUpdate
  void touch() {
    updatedAt = Instant.now();
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

  public BigDecimal getAvailableBalance() {
    return availableBalance;
  }

  public void setAvailableBalance(BigDecimal availableBalance) {
    this.availableBalance = availableBalance;
  }

  public BigDecimal getPendingBalance() {
    return pendingBalance;
  }

  public void setPendingBalance(BigDecimal pendingBalance) {
    this.pendingBalance = pendingBalance;
  }

  public BigDecimal getTotalEarnings() {
    return totalEarnings;
  }

  public void setTotalEarnings(BigDecimal totalEarnings) {
    this.totalEarnings = totalEarnings;
  }

  public BigDecimal getTotalWithdrawn() {
    return totalWithdrawn;
  }

  public void setTotalWithdrawn(BigDecimal totalWithdrawn) {
    this.totalWithdrawn = totalWithdrawn;
  }
}
