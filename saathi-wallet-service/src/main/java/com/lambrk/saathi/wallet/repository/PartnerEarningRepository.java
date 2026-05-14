package com.lambrk.saathi.wallet.repository;

import com.lambrk.saathi.wallet.entity.PartnerEarning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerEarningRepository extends JpaRepository<PartnerEarning, Long> {
    List<PartnerEarning> findByPartnerIdOrderByIdDesc(Long partnerId);
}
