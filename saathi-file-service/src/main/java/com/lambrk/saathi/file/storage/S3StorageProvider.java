package com.lambrk.saathi.file.storage;

import org.springframework.stereotype.Component;

@Component
public class S3StorageProvider implements FileStorageProvider {
    @Override
    public String store(String objectKey, byte[] content) {
        return "s3://" + objectKey;
    }
}
