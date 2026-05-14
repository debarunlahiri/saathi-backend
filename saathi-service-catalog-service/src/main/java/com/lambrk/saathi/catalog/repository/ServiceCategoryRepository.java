package com.lambrk.saathi.catalog.repository;

import com.lambrk.saathi.catalog.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
    List<ServiceCategory> findByActiveTrue();
}
