package com.lambrk.saathi.pricing.repository;

import com.lambrk.saathi.pricing.entity.PricingRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PricingRuleRepository extends JpaRepository<PricingRule, Long> {
    Optional<PricingRule> findFirstByServiceCategoryIdAndActiveTrueOrderByIdDesc(Long serviceCategoryId);
}
