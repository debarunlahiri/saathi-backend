package com.lambrk.saathi.file.storage;

import org.springframework.stereotype.Component;

@Component
public class CloudinaryStorageProvider implements FileStorageProvider {
    @Override
    public String store(String objectKey, byte[] content) {
        return "cloudinary://" + objectKey;
    }
}
