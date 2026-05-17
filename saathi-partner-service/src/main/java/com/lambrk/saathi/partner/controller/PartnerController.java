package com.lambrk.saathi.partner.controller;

import com.lambrk.saathi.partner.dto.request.*;
import com.lambrk.saathi.partner.dto.response.ApiResponse;
import com.lambrk.saathi.partner.entity.PartnerKyc;
import com.lambrk.saathi.partner.entity.PartnerProfile;
import com.lambrk.saathi.partner.service.PartnerService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {
  private final PartnerService partnerService;

  public PartnerController(PartnerService partnerService) {
    this.partnerService = partnerService;
  }

  @PostMapping
  public ApiResponse<PartnerProfile> createOrUpdate(
      @Valid @RequestBody PartnerProfileRequest request) {
    return ApiResponse.success(
        "Partner profile saved successfully", partnerService.createOrUpdate(request));
  }

  @GetMapping("/{partnerId}")
  public ApiResponse<PartnerProfile> get(@PathVariable Long partnerId) {
    return ApiResponse.success("Partner fetched successfully", partnerService.get(partnerId));
  }

  @PostMapping("/{partnerId}/kyc")
  public ApiResponse<PartnerKyc> submitKyc(
      @PathVariable Long partnerId, @Valid @RequestBody KycRequest request) {
    return ApiResponse.success(
        "Partner KYC submitted successfully", partnerService.submitKyc(partnerId, request));
  }

  @PutMapping("/{partnerId}/availability")
  public ApiResponse<PartnerProfile> availability(
      @PathVariable Long partnerId, @Valid @RequestBody AvailabilityRequest request) {
    return ApiResponse.success(
        "Partner availability updated successfully",
        partnerService.availability(partnerId, request));
  }

  @PutMapping("/{partnerId}/location")
  public ApiResponse<PartnerProfile> location(
      @PathVariable Long partnerId, @Valid @RequestBody LocationRequest request) {
    return ApiResponse.success(
        "Partner location updated successfully", partnerService.location(partnerId, request));
  }

  @GetMapping("/available")
  public ApiResponse<List<PartnerProfile>> availablePartners() {
    return ApiResponse.success(
        "Available partners fetched successfully", partnerService.availablePartners());
  }

  @GetMapping("/admin/counts/pending-kyc")
  public ApiResponse<Long> pendingKycCount() {
    return ApiResponse.success(
        "Pending KYC count fetched successfully", partnerService.pendingKycCount());
  }

  @PutMapping("/admin/{partnerId}/kyc/approve")
  public ApiResponse<PartnerProfile> approveKyc(@PathVariable Long partnerId) {
    return ApiResponse.success(
        "Partner KYC approved successfully", partnerService.approveKyc(partnerId));
  }

  @PutMapping("/admin/{partnerId}/kyc/reject")
  public ApiResponse<PartnerProfile> rejectKyc(
      @PathVariable Long partnerId, @RequestBody RejectKycRequest request) {
    return ApiResponse.success(
        "Partner KYC rejected successfully", partnerService.rejectKyc(partnerId, request.reason()));
  }

  public record RejectKycRequest(String reason) {}
}
