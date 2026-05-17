package com.lambrk.saathi.partner.entity;

import com.lambrk.saathi.partner.enums.KycStatus;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "partner_kyc")
public class PartnerKyc {
  @Id
  @Column(columnDefinition = "uuid", updatable = false, nullable = false)
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "partner_id", nullable = false, unique = true)
  private PartnerProfile partner;

  @Column(name = "aadhaar_url")
  private String aadhaarUrl;

  @Column(name = "pan_url")
  private String panUrl;

  @Column(name = "address_proof_url")
  private String addressProofUrl;

  @Column(name = "profile_photo_url")
  private String profilePhotoUrl;

  @Column(name = "bank_account_or_upi")
  private String bankAccountOrUpi;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private KycStatus status = KycStatus.PENDING;

  @Column(name = "rejection_reason")
  private String rejectionReason;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @PrePersist
  void prePersist() {
    generateId();
    createdAt = Instant.now();
  }

  void generateId() {
    if (id == null) {
      id = com.github.f4b6a3.uuid.UuidCreator.getTimeOrderedEpoch();
    }
  }

  public UUID getId() {
    return id;
  }

  public PartnerProfile getPartner() {
    return partner;
  }

  public void setPartner(PartnerProfile partner) {
    this.partner = partner;
  }

  public String getAadhaarUrl() {
    return aadhaarUrl;
  }

  public void setAadhaarUrl(String aadhaarUrl) {
    this.aadhaarUrl = aadhaarUrl;
  }

  public String getPanUrl() {
    return panUrl;
  }

  public void setPanUrl(String panUrl) {
    this.panUrl = panUrl;
  }

  public String getAddressProofUrl() {
    return addressProofUrl;
  }

  public void setAddressProofUrl(String addressProofUrl) {
    this.addressProofUrl = addressProofUrl;
  }

  public String getProfilePhotoUrl() {
    return profilePhotoUrl;
  }

  public void setProfilePhotoUrl(String profilePhotoUrl) {
    this.profilePhotoUrl = profilePhotoUrl;
  }

  public String getBankAccountOrUpi() {
    return bankAccountOrUpi;
  }

  public void setBankAccountOrUpi(String bankAccountOrUpi) {
    this.bankAccountOrUpi = bankAccountOrUpi;
  }

  public KycStatus getStatus() {
    return status;
  }

  public void setStatus(KycStatus status) {
    this.status = status;
  }

  public String getRejectionReason() {
    return rejectionReason;
  }

  public void setRejectionReason(String rejectionReason) {
    this.rejectionReason = rejectionReason;
  }
}
