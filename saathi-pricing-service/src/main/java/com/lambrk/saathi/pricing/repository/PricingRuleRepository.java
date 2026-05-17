package com.lambrk.saathi.pricing.repository;

import com.lambrk.saathi.pricing.entity.PricingRule;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingRuleRepository extends JpaRepository<PricingRule, Long> {
  Optional<PricingRule> findFirstByServiceCategoryIdAndActiveTrueOrderByIdDesc(
      Long serviceCategoryId);
}
