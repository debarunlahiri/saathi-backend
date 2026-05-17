package com.lambrk.saathi.customer.repository;

import com.lambrk.saathi.customer.entity.CustomerProfile;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, UUID> {
  Optional<CustomerProfile> findByIdentityUserId(UUID identityUserId);
}
