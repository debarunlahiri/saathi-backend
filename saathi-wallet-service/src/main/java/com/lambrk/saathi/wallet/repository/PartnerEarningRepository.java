package com.lambrk.saathi.wallet.repository;

import com.lambrk.saathi.wallet.entity.PartnerEarning;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerEarningRepository extends JpaRepository<PartnerEarning, UUID> {
  List<PartnerEarning> findByPartnerIdOrderByIdDesc(UUID partnerId);
}
