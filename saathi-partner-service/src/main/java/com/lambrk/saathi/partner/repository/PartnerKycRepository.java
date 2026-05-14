package com.lambrk.saathi.partner.repository;

import com.lambrk.saathi.partner.entity.PartnerKyc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerKycRepository extends JpaRepository<PartnerKyc, Long> {
    Optional<PartnerKyc> findByPartnerId(Long partnerId);
}
