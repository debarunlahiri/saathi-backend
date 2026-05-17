package com.lambrk.saathi.customer.service;

import com.lambrk.saathi.customer.dto.request.CustomerProfileRequest;
import com.lambrk.saathi.customer.entity.CustomerProfile;
import com.lambrk.saathi.customer.repository.CustomerProfileRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  private final CustomerProfileRepository repository;

  public CustomerService(CustomerProfileRepository repository) {
    this.repository = repository;
  }

  public CustomerProfile save(CustomerProfileRequest request) {
    CustomerProfile profile =
        repository.findByIdentityUserId(request.identityUserId()).orElseGet(CustomerProfile::new);
    profile.setIdentityUserId(request.identityUserId());
    profile.setFullName(request.fullName());
    profile.setMobileNumber(request.mobileNumber());
    profile.setEmail(request.email());
    profile.setDefaultAddress(request.defaultAddress());
    profile.setDefaultLatitude(request.defaultLatitude());
    profile.setDefaultLongitude(request.defaultLongitude());
    profile.setEmergencyContact(request.emergencyContact());
    return repository.save(profile);
  }

  public CustomerProfile get(UUID customerId) {
    return repository.findById(customerId).orElseThrow();
  }
}
