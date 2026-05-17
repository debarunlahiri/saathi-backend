package com.lambrk.saathi.partner.entity;

import com.lambrk.saathi.partner.enums.AvailabilityStatus;
import com.lambrk.saathi.partner.enums.KycStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(
    name = "partner_profiles",
    indexes = {
      @Index(name = "idx_partner_identity_user", columnList = "identity_user_id", unique = true),
      @Index(name = "idx_partner_availability_kyc", columnList = "availability_status,kyc_status")
    })
public class PartnerProfile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "identity_user_id", nullable = false, unique = true)
  private Long identityUserId;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "mobile_number", nullable = false)
  private String mobileNumber;

  private String email;
  private String address;

  @Enumerated(EnumType.STRING)
  @Column(name = "kyc_status", nullable = false)
  private KycStatus kycStatus = KycStatus.PENDING;

  @Enumerated(EnumType.STRING)
  @Column(name = "availability_status", nullable = false)
  private AvailabilityStatus availabilityStatus = AvailabilityStatus.OFFLINE;

  @Column(name = "current_latitude", precision = 10, scale = 7)
  private BigDecimal currentLatitude;

  @Column(name = "current_longitude", precision = 10, scale = 7)
  private BigDecimal currentLongitude;

  @Column(name = "service_radius_km", precision = 5, scale = 2)
  private BigDecimal serviceRadiusKm = BigDecimal.valueOf(3);

  @Column(name = "average_rating", precision = 3, scale = 2)
  private BigDecimal averageRating = BigDecimal.ZERO;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  @PrePersist
  void prePersist() {
    createdAt = Instant.now();
    updatedAt = createdAt;
  }

  @PreUpdate
  void preUpdate() {
    updatedAt = Instant.now();
  }

  public Long getId() {
    return id;
  }

  public Long getIdentityUserId() {
    return identityUserId;
  }

  public void setIdentityUserId(Long identityUserId) {
    this.identityUserId = identityUserId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public KycStatus getKycStatus() {
    return kycStatus;
  }

  public void setKycStatus(KycStatus kycStatus) {
    this.kycStatus = kycStatus;
  }

  public AvailabilityStatus getAvailabilityStatus() {
    return availabilityStatus;
  }

  public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
    this.availabilityStatus = availabilityStatus;
  }

  public BigDecimal getCurrentLatitude() {
    return currentLatitude;
  }

  public void setCurrentLatitude(BigDecimal currentLatitude) {
    this.currentLatitude = currentLatitude;
  }

  public BigDecimal getCurrentLongitude() {
    return currentLongitude;
  }

  public void setCurrentLongitude(BigDecimal currentLongitude) {
    this.currentLongitude = currentLongitude;
  }

  public BigDecimal getServiceRadiusKm() {
    return serviceRadiusKm;
  }

  public void setServiceRadiusKm(BigDecimal serviceRadiusKm) {
    this.serviceRadiusKm = serviceRadiusKm;
  }

  public BigDecimal getAverageRating() {
    return averageRating;
  }

  public void setAverageRating(BigDecimal averageRating) {
    this.averageRating = averageRating;
  }
}
