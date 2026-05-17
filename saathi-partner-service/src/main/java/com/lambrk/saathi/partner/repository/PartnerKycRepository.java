package com.lambrk.saathi.partner.repository;

import com.lambrk.saathi.partner.entity.PartnerKyc;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerKycRepository extends JpaRepository<PartnerKyc, UUID> {
  Optional<PartnerKyc> findByPartnerId(UUID partnerId);
}
