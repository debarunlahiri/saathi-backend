package com.lambrk.saathi.customer.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "customer_profiles", indexes = @Index(name = "idx_customer_identity_user", columnList = "identity_user_id", unique = true))
public class CustomerProfile {
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
    @Column(name = "default_address")
    private String defaultAddress;
    @Column(name = "default_latitude", precision = 10, scale = 7)
    private BigDecimal defaultLatitude;
    @Column(name = "default_longitude", precision = 10, scale = 7)
    private BigDecimal defaultLongitude;
    @Column(name = "emergency_contact")
    private String emergencyContact;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    void prePersist() { createdAt = Instant.now(); updatedAt = createdAt; }
    @PreUpdate
    void preUpdate() { updatedAt = Instant.now(); }

    public Long getId() { return id; }
    public Long getIdentityUserId() { return identityUserId; }
    public void setIdentityUserId(Long identityUserId) { this.identityUserId = identityUserId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDefaultAddress() { return defaultAddress; }
    public void setDefaultAddress(String defaultAddress) { this.defaultAddress = defaultAddress; }
    public BigDecimal getDefaultLatitude() { return defaultLatitude; }
    public void setDefaultLatitude(BigDecimal defaultLatitude) { this.defaultLatitude = defaultLatitude; }
    public BigDecimal getDefaultLongitude() { return defaultLongitude; }
    public void setDefaultLongitude(BigDecimal defaultLongitude) { this.defaultLongitude = defaultLongitude; }
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
}
