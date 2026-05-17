package com.lambrk.saathi.catalog.service;

import com.lambrk.saathi.catalog.dto.ServiceCategoryRequest;
import com.lambrk.saathi.catalog.entity.ServiceCategory;
import com.lambrk.saathi.catalog.repository.ServiceCategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ServiceCatalogService {
  private final ServiceCategoryRepository repository;

  public ServiceCatalogService(ServiceCategoryRepository repository) {
    this.repository = repository;
  }

  public List<ServiceCategory> active() {
    return repository.findByActiveTrue();
  }

  public ServiceCategory save(ServiceCategoryRequest request, Long id) {
    ServiceCategory category =
        id == null ? new ServiceCategory() : repository.findById(id).orElseThrow();
    category.setName(request.name());
    category.setCode(request.code());
    category.setDescription(request.description());
    category.setIconUrl(request.iconUrl());
    category.setBasePrice(request.basePrice());
    category.setActive(request.active() == null || request.active());
    return repository.save(category);
  }
}
