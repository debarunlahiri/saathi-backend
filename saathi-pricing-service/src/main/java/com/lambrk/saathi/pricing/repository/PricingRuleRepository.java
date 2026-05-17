package com.lambrk.saathi.pricing.repository;

import com.lambrk.saathi.pricing.entity.PricingRule;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingRuleRepository extends JpaRepository<PricingRule, UUID> {
  Optional<PricingRule> findFirstByServiceCategoryIdAndActiveTrueOrderByIdDesc(
      UUID serviceCategoryId);
}
