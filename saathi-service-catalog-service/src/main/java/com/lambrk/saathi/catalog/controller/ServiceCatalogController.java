package com.lambrk.saathi.catalog.controller;

import com.lambrk.saathi.catalog.dto.ApiResponse;
import com.lambrk.saathi.catalog.dto.ServiceCategoryRequest;
import com.lambrk.saathi.catalog.entity.ServiceCategory;
import com.lambrk.saathi.catalog.service.ServiceCatalogService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catalog/categories")
public class ServiceCatalogController {
  private final ServiceCatalogService service;

  public ServiceCatalogController(ServiceCatalogService service) {
    this.service = service;
  }

  @GetMapping
  public ApiResponse<List<ServiceCategory>> active() {
    return ApiResponse.success("Categories fetched successfully", service.active());
  }

  @PostMapping
  public ApiResponse<ServiceCategory> create(@Valid @RequestBody ServiceCategoryRequest request) {
    return ApiResponse.success("Category created successfully", service.save(request, null));
  }

  @PutMapping("/{id}")
  public ApiResponse<ServiceCategory> update(
      @PathVariable Long id, @Valid @RequestBody ServiceCategoryRequest request) {
    return ApiResponse.success("Category updated successfully", service.save(request, id));
  }
}
