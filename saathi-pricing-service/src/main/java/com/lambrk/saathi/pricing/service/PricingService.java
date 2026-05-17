package com.lambrk.saathi.pricing.service;

import com.lambrk.saathi.pricing.dto.PricingEstimateRequest;
import com.lambrk.saathi.pricing.dto.PricingEstimateResponse;
import com.lambrk.saathi.pricing.dto.PricingRuleRequest;
import com.lambrk.saathi.pricing.entity.PricingRule;
import com.lambrk.saathi.pricing.repository.PricingRuleRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PricingService {
  private final PricingRuleRepository repository;

  public PricingService(PricingRuleRepository repository) {
    this.repository = repository;
  }

  public List<PricingRule> rules() {
    return repository.findAll();
  }

  public PricingRule save(PricingRuleRequest request) {
    PricingRule rule = new PricingRule();
    rule.setServiceCategoryId(request.serviceCategoryId());
    rule.setBasePrice(request.basePrice());
    if (request.includedDistanceKm() != null)
      rule.setIncludedDistanceKm(request.includedDistanceKm());
    if (request.perKmCharge() != null) rule.setPerKmCharge(request.perKmCharge());
    if (request.includedWaitingMinutes() != null)
      rule.setIncludedWaitingMinutes(request.includedWaitingMinutes());
    if (request.perWaitingUnitMinutes() != null)
      rule.setPerWaitingUnitMinutes(request.perWaitingUnitMinutes());
    if (request.perWaitingUnitCharge() != null)
      rule.setPerWaitingUnitCharge(request.perWaitingUnitCharge());
    if (request.urgentCharge() != null) rule.setUrgentCharge(request.urgentCharge());
    if (request.platformFee() != null) rule.setPlatformFee(request.platformFee());
    rule.setActive(request.active() == null || request.active());
    return repository.save(rule);
  }

  public PricingEstimateResponse estimate(PricingEstimateRequest request) {
    PricingRule rule =
        repository
            .findFirstByServiceCategoryIdAndActiveTrueOrderByIdDesc(request.serviceCategoryId())
            .orElseThrow();
    BigDecimal distanceKm = request.distanceKm() == null ? BigDecimal.ZERO : request.distanceKm();
    BigDecimal billableDistance =
        distanceKm.subtract(rule.getIncludedDistanceKm()).max(BigDecimal.ZERO);
    BigDecimal distanceCharge =
        billableDistance.multiply(rule.getPerKmCharge()).setScale(2, RoundingMode.HALF_UP);
    int waiting = request.waitingMinutes() == null ? 0 : request.waitingMinutes();
    int billableWaiting = Math.max(waiting - rule.getIncludedWaitingMinutes(), 0);
    int units =
        (int) Math.ceil((double) billableWaiting / Math.max(rule.getPerWaitingUnitMinutes(), 1));
    BigDecimal waitingCharge =
        rule.getPerWaitingUnitCharge()
            .multiply(BigDecimal.valueOf(units))
            .setScale(2, RoundingMode.HALF_UP);
    BigDecimal urgency = request.urgent() ? rule.getUrgentCharge() : BigDecimal.ZERO;
    BigDecimal discount = request.discount() == null ? BigDecimal.ZERO : request.discount();
    BigDecimal total =
        rule.getBasePrice()
            .add(distanceCharge)
            .add(waitingCharge)
            .add(urgency)
            .add(rule.getPlatformFee())
            .subtract(discount)
            .max(BigDecimal.ZERO);
    return new PricingEstimateResponse(
        rule.getBasePrice(),
        distanceCharge,
        waitingCharge,
        urgency,
        rule.getPlatformFee(),
        discount,
        total);
  }
}
