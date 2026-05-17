package com.lambrk.saathi.location.controller;

import com.lambrk.saathi.location.dto.ApiResponse;
import com.lambrk.saathi.location.dto.LocationUpdateRequest;
import com.lambrk.saathi.location.entity.LocationEvent;
import com.lambrk.saathi.location.service.LocationService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
  private final LocationService service;

  public LocationController(LocationService service) {
    this.service = service;
  }

  @PostMapping("/update")
  public ApiResponse<LocationEvent> update(@Valid @RequestBody LocationUpdateRequest request) {
    return ApiResponse.success("Location updated successfully", service.update(request));
  }

  @GetMapping("/tasks/{taskId}/latest")
  public ApiResponse<LocationEvent> latest(@PathVariable UUID taskId) {
    return ApiResponse.success("Latest location fetched successfully", service.latest(taskId));
  }

  @GetMapping("/tasks/{taskId}/history")
  public ApiResponse<List<LocationEvent>> history(@PathVariable UUID taskId) {
    return ApiResponse.success("Location history fetched successfully", service.history(taskId));
  }
}
