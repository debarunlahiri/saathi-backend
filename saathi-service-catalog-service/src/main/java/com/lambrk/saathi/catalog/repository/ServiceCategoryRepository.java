package com.lambrk.saathi.catalog.repository;

import com.lambrk.saathi.catalog.entity.ServiceCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
  List<ServiceCategory> findByActiveTrue();
}
