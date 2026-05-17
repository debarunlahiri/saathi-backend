package com.lambrk.saathi.wallet.repository;

import com.lambrk.saathi.wallet.entity.PartnerPayout;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerPayoutRepository extends JpaRepository<PartnerPayout, UUID> {
  List<PartnerPayout> findByPartnerIdOrderByIdDesc(UUID partnerId);
}
