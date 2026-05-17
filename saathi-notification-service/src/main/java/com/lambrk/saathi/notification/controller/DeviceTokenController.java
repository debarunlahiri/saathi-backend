package com.lambrk.saathi.notification.controller;

import com.lambrk.saathi.notification.dto.ApiResponse;
import com.lambrk.saathi.notification.dto.RegisterDeviceTokenRequest;
import com.lambrk.saathi.notification.service.DeviceTokenService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/device-tokens")
public class DeviceTokenController {
  private final DeviceTokenService service;

  public DeviceTokenController(DeviceTokenService service) {
    this.service = service;
  }

  @PostMapping
  public ApiResponse<Void> register(@Valid @RequestBody RegisterDeviceTokenRequest request) {
    service.register(request);
    return ApiResponse.success("Device token registered successfully", null);
  }

  @DeleteMapping
  public ApiResponse<Void> unregister(@Valid @RequestBody RegisterDeviceTokenRequest request) {
    service.unregister(request);
    return ApiResponse.success("Device token unregistered successfully", null);
  }

  @GetMapping("/users/{userId}")
  public ApiResponse<List<String>> tokens(@PathVariable Long userId) {
    return ApiResponse.success("Device tokens fetched successfully", service.tokens(userId));
  }
}
