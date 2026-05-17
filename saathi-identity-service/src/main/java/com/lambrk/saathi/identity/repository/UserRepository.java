package com.lambrk.saathi.identity.repository;

import com.lambrk.saathi.identity.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByMobileNumber(String mobileNumber);

  boolean existsByMobileNumber(String mobileNumber);
}
