package com.lambrk.saathi.file.service;

import com.lambrk.saathi.file.dto.RegisterFileRequest;
import com.lambrk.saathi.file.entity.FileAsset;
import com.lambrk.saathi.file.repository.FileAssetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileAssetService {
    private final FileAssetRepository repository;
    public FileAssetService(FileAssetRepository repository) { this.repository = repository; }
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
    public List<FileAsset> byReference(String referenceType, String referenceId) { return repository.findByReferenceTypeAndReferenceIdOrderByIdDesc(referenceType, referenceId); }
}
