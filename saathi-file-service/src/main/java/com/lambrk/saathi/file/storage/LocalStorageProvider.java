package com.lambrk.saathi.file.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocalStorageProvider implements FileStorageProvider {
  private static final Logger log = LoggerFactory.getLogger(LocalStorageProvider.class);

  private final Path storageRoot;

  public LocalStorageProvider(
      @Value("${saathi.file.storage-root:./data/files}") String storageRoot) {
    this.storageRoot = Paths.get(storageRoot).toAbsolutePath().normalize();
    try {
      Files.createDirectories(this.storageRoot);
    } catch (IOException e) {
      throw new RuntimeException("Failed to create storage directory: " + this.storageRoot, e);
    }
  }

  @Override
  public String store(String objectKey, byte[] content) {
    String fileName = UUID.randomUUID() + "_" + sanitize(objectKey);
    Path filePath = storageRoot.resolve(fileName);
    try {
      Files.write(filePath, content);
      log.info("File stored at {}", filePath);
    } catch (IOException e) {
      throw new RuntimeException("Failed to write file: " + filePath, e);
    }
    return "/api/files/download/" + fileName;
  }

  private String sanitize(String name) {
    return name.replaceAll("[^a-zA-Z0-9._-]", "_");
  }
}
