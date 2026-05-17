package com.lambrk.saathi.admin.controller;

import com.lambrk.saathi.admin.dto.ApiResponse;
import com.lambrk.saathi.admin.dto.DashboardResponse;
import com.lambrk.saathi.admin.service.AdminDashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminDashboardController {
  private final AdminDashboardService service;

  public AdminDashboardController(AdminDashboardService service) {
    this.service = service;
  }

  @GetMapping("/dashboard")
  public ApiResponse<DashboardResponse> dashboard() {
    return ApiResponse.success("Dashboard fetched successfully", service.dashboard());
  }

  @GetMapping("/reports")
  public ApiResponse<DashboardResponse> reports() {
    return ApiResponse.success("Report generated successfully", service.dashboard());
  }
}
