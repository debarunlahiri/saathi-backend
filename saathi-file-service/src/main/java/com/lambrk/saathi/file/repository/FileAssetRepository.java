package com.lambrk.saathi.file.repository;

import com.lambrk.saathi.file.entity.FileAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileAssetRepository extends JpaRepository<FileAsset, Long> {
    List<FileAsset> findByReferenceTypeAndReferenceIdOrderByIdDesc(String referenceType, String referenceId);
}
