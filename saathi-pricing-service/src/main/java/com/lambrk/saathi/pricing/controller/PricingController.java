package com.lambrk.saathi.pricing.controller;

import com.lambrk.saathi.pricing.dto.*;
import com.lambrk.saathi.pricing.entity.PricingRule;
import com.lambrk.saathi.pricing.service.PricingService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pricing")
public class PricingController {
  private final PricingService service;

  public PricingController(PricingService service) {
    this.service = service;
  }

  @PostMapping("/estimate")
  public ApiResponse<PricingEstimateResponse> estimate(
      @Valid @RequestBody PricingEstimateRequest request) {
    return ApiResponse.success("Price estimated successfully", service.estimate(request));
  }

  @GetMapping("/rules")
  public ApiResponse<List<PricingRule>> rules() {
    return ApiResponse.success("Pricing rules fetched successfully", service.rules());
  }

  @PostMapping("/rules")
  public ApiResponse<PricingRule> create(@Valid @RequestBody PricingRuleRequest request) {
    return ApiResponse.success("Pricing rule created successfully", service.save(request));
  }
}
