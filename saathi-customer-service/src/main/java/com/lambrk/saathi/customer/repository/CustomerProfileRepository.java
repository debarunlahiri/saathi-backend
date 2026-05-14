package com.lambrk.saathi.customer.repository;

import com.lambrk.saathi.customer.entity.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
    Optional<CustomerProfile> findByIdentityUserId(Long identityUserId);
}
