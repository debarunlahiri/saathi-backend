package com.lambrk.saathi.file.repository;

import com.lambrk.saathi.file.entity.FileAsset;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileAssetRepository extends JpaRepository<FileAsset, Long> {
  List<FileAsset> findByReferenceTypeAndReferenceIdOrderByIdDesc(
      String referenceType, String referenceId);

  Optional<FileAsset> findByStorageUrl(String storageUrl);
}
