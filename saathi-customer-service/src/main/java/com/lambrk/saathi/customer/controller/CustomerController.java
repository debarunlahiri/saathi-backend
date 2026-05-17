package com.lambrk.saathi.customer.controller;

import com.lambrk.saathi.customer.dto.request.CustomerProfileRequest;
import com.lambrk.saathi.customer.dto.response.ApiResponse;
import com.lambrk.saathi.customer.entity.CustomerProfile;
import com.lambrk.saathi.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping
  public ApiResponse<CustomerProfile> save(@Valid @RequestBody CustomerProfileRequest request) {
    return ApiResponse.success(
        "Customer profile saved successfully", customerService.save(request));
  }

  @GetMapping("/{customerId}")
  public ApiResponse<CustomerProfile> get(@PathVariable Long customerId) {
    return ApiResponse.success(
        "Customer profile fetched successfully", customerService.get(customerId));
  }
}
