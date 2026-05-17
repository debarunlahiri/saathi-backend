package com.lambrk.saathi.partner.repository;

import com.lambrk.saathi.partner.entity.PartnerKyc;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerKycRepository extends JpaRepository<PartnerKyc, Long> {
  Optional<PartnerKyc> findByPartnerId(Long partnerId);
}
