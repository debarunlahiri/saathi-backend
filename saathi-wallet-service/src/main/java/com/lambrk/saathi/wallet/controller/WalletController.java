package com.lambrk.saathi.wallet.controller;

import com.lambrk.saathi.wallet.dto.ApiResponse;
import com.lambrk.saathi.wallet.dto.CreateEarningRequest;
import com.lambrk.saathi.wallet.dto.PayoutRequest;
import com.lambrk.saathi.wallet.entity.PartnerEarning;
import com.lambrk.saathi.wallet.entity.PartnerPayout;
import com.lambrk.saathi.wallet.entity.PartnerWallet;
import com.lambrk.saathi.wallet.service.WalletService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
  private final WalletService service;

  public WalletController(WalletService service) {
    this.service = service;
  }

  @GetMapping("/partners/{partnerId}")
  public ApiResponse<PartnerWallet> wallet(@PathVariable Long partnerId) {
    return ApiResponse.success("Wallet fetched successfully", service.wallet(partnerId));
  }

  @GetMapping("/partners/{partnerId}/earnings")
  public ApiResponse<List<PartnerEarning>> earnings(@PathVariable Long partnerId) {
    return ApiResponse.success("Earnings fetched successfully", service.earnings(partnerId));
  }

  @PostMapping("/earnings")
  public ApiResponse<PartnerEarning> addEarning(@Valid @RequestBody CreateEarningRequest request) {
    return ApiResponse.success("Earning created successfully", service.addEarning(request));
  }

  @PostMapping("/payouts")
  public ApiResponse<PartnerPayout> payout(@Valid @RequestBody PayoutRequest request) {
    return ApiResponse.success("Payout initiated successfully", service.payout(request));
  }

  @GetMapping("/partners/{partnerId}/payouts")
  public ApiResponse<List<PartnerPayout>> payouts(@PathVariable Long partnerId) {
    return ApiResponse.success("Payouts fetched successfully", service.payouts(partnerId));
  }

  @PostMapping("/partners/{partnerId}/settle")
  public ApiResponse<PartnerWallet> settle(@PathVariable Long partnerId) {
    return ApiResponse.success(
        "Earnings settled to available balance", service.settleEarnings(partnerId));
  }
}
