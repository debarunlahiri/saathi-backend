package com.lambrk.saathi.wallet.repository;

import com.lambrk.saathi.wallet.entity.PartnerEarning;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerEarningRepository extends JpaRepository<PartnerEarning, Long> {
  List<PartnerEarning> findByPartnerIdOrderByIdDesc(Long partnerId);
}
