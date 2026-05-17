package com.lambrk.saathi.file.service;

import com.lambrk.saathi.file.dto.RegisterFileRequest;
import com.lambrk.saathi.file.entity.FileAsset;
import com.lambrk.saathi.file.repository.FileAssetRepository;
import com.lambrk.saathi.file.storage.FileStorageProvider;
import com.lambrk.saathi.file.storage.FileStorageRegistry;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FileAssetService {
  private final FileAssetRepository repository;
  private final FileStorageProvider storageProvider;

  public FileAssetService(FileAssetRepository repository, FileStorageRegistry storageRegistry) {
    this.repository = repository;
    this.storageProvider = storageRegistry.getDefault();
  }

  public FileAsset register(RegisterFileRequest request) {
    FileAsset asset = new FileAsset();
    asset.setOwnerUserId(request.ownerUserId());
    asset.setReferenceType(request.referenceType());
    asset.setReferenceId(request.referenceId());
    asset.setFileName(request.fileName());
    asset.setContentType(request.contentType());
    asset.setStorageUrl(request.storageUrl());
    return repository.save(asset);
  }

  public FileAsset upload(
      Long ownerUserId,
      String referenceType,
      String referenceId,
      String fileName,
      String contentType,
      byte[] content) {
    String storageUrl = storageProvider.store(fileName, content);
    FileAsset asset = new FileAsset();
    asset.setOwnerUserId(ownerUserId);
    asset.setReferenceType(referenceType);
    asset.setReferenceId(referenceId);
    asset.setFileName(fileName);
    asset.setContentType(contentType);
    asset.setStorageUrl(storageUrl);
    return repository.save(asset);
  }

  public List<FileAsset> byReference(String referenceType, String referenceId) {
    return repository.findByReferenceTypeAndReferenceIdOrderByIdDesc(referenceType, referenceId);
  }

  public FileAsset download(String storageUrl) {
    return repository.findByStorageUrl(storageUrl).orElse(null);
  }
}
