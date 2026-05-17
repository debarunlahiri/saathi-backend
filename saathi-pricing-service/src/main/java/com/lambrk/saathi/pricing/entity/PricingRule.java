package com.lambrk.saathi.pricing.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "pricing_rules")
public class PricingRule {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "service_category_id", nullable = false)
  private Long serviceCategoryId;

  @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
  private BigDecimal basePrice = BigDecimal.ZERO;

  @Column(name = "included_distance_km", precision = 8, scale = 2)
  private BigDecimal includedDistanceKm = BigDecimal.ONE;

  @Column(name = "per_km_charge", precision = 10, scale = 2)
  private BigDecimal perKmCharge = BigDecimal.ZERO;

  @Column(name = "included_waiting_minutes")
  private int includedWaitingMinutes = 10;

  @Column(name = "per_waiting_unit_minutes")
  private int perWaitingUnitMinutes = 10;

  @Column(name = "per_waiting_unit_charge", precision = 10, scale = 2)
  private BigDecimal perWaitingUnitCharge = BigDecimal.ZERO;

  @Column(name = "urgent_charge", precision = 10, scale = 2)
  private BigDecimal urgentCharge = BigDecimal.ZERO;

  @Column(name = "platform_fee", precision = 10, scale = 2)
  private BigDecimal platformFee = BigDecimal.ZERO;

  private boolean active = true;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @PrePersist
  void prePersist() {
    createdAt = Instant.now();
  }

  public Long getId() {
    return id;
  }

  public Long getServiceCategoryId() {
    return serviceCategoryId;
  }

  public void setServiceCategoryId(Long serviceCategoryId) {
    this.serviceCategoryId = serviceCategoryId;
  }

  public BigDecimal getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(BigDecimal basePrice) {
    this.basePrice = basePrice;
  }

  public BigDecimal getIncludedDistanceKm() {
    return includedDistanceKm;
  }

  public void setIncludedDistanceKm(BigDecimal includedDistanceKm) {
    this.includedDistanceKm = includedDistanceKm;
  }

  public BigDecimal getPerKmCharge() {
    return perKmCharge;
  }

  public void setPerKmCharge(BigDecimal perKmCharge) {
    this.perKmCharge = perKmCharge;
  }

  public int getIncludedWaitingMinutes() {
    return includedWaitingMinutes;
  }

  public void setIncludedWaitingMinutes(int includedWaitingMinutes) {
    this.includedWaitingMinutes = includedWaitingMinutes;
  }

  public int getPerWaitingUnitMinutes() {
    return perWaitingUnitMinutes;
  }

  public void setPerWaitingUnitMinutes(int perWaitingUnitMinutes) {
    this.perWaitingUnitMinutes = perWaitingUnitMinutes;
  }

  public BigDecimal getPerWaitingUnitCharge() {
    return perWaitingUnitCharge;
  }

  public void setPerWaitingUnitCharge(BigDecimal perWaitingUnitCharge) {
    this.perWaitingUnitCharge = perWaitingUnitCharge;
  }

  public BigDecimal getUrgentCharge() {
    return urgentCharge;
  }

  public void setUrgentCharge(BigDecimal urgentCharge) {
    this.urgentCharge = urgentCharge;
  }

  public BigDecimal getPlatformFee() {
    return platformFee;
  }

  public void setPlatformFee(BigDecimal platformFee) {
    this.platformFee = platformFee;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
