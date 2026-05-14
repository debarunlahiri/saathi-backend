package com.lambrk.saathi.file.storage;

import org.springframework.stereotype.Component;

@Component
public class LocalStorageProvider implements FileStorageProvider {
    @Override
    public String store(String objectKey, byte[] content) {
        return "local://" + objectKey;
    }
}
