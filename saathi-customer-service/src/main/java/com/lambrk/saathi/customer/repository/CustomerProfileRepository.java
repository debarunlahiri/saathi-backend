package com.lambrk.saathi.customer.repository;

import com.lambrk.saathi.customer.entity.CustomerProfile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
  Optional<CustomerProfile> findByIdentityUserId(Long identityUserId);
}
