package com.lambrk.saathi.partner.controller;

import com.lambrk.saathi.partner.dto.request.*;
import com.lambrk.saathi.partner.dto.response.ApiResponse;
import com.lambrk.saathi.partner.entity.PartnerKyc;
import com.lambrk.saathi.partner.entity.PartnerProfile;
import com.lambrk.saathi.partner.service.PartnerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {
    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @PostMapping
    public ApiResponse<PartnerProfile> createOrUpdate(@Valid @RequestBody PartnerProfileRequest request) {
        return ApiResponse.success("Partner profile saved successfully", partnerService.createOrUpdate(request));
    }

    @GetMapping("/{partnerId}")
    public ApiResponse<PartnerProfile> get(@PathVariable Long partnerId) {
        return ApiResponse.success("Partner fetched successfully", partnerService.get(partnerId));
    }

    @PostMapping("/{partnerId}/kyc")
    public ApiResponse<PartnerKyc> submitKyc(@PathVariable Long partnerId, @Valid @RequestBody KycRequest request) {
        return ApiResponse.success("Partner KYC submitted successfully", partnerService.submitKyc(partnerId, request));
    }

    @PutMapping("/{partnerId}/availability")
    public ApiResponse<PartnerProfile> availability(@PathVariable Long partnerId, @Valid @RequestBody AvailabilityRequest request) {
        return ApiResponse.success("Partner availability updated successfully", partnerService.availability(partnerId, request));
    }

    @PutMapping("/{partnerId}/location")
    public ApiResponse<PartnerProfile> location(@PathVariable Long partnerId, @Valid @RequestBody LocationRequest request) {
        return ApiResponse.success("Partner location updated successfully", partnerService.location(partnerId, request));
    }

    @GetMapping("/available")
    public ApiResponse<List<PartnerProfile>> availablePartners() {
        return ApiResponse.success("Available partners fetched successfully", partnerService.availablePartners());
    }
}
