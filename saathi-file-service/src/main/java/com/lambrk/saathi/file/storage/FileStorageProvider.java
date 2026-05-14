package com.lambrk.saathi.file.storage;

public interface FileStorageProvider {
    String store(String objectKey, byte[] content);
}
