package com.lambrk.saathi.file.storage;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FileStorageRegistry {

  private final FileStorageProvider defaultProvider;
  private final List<FileStorageProvider> providers;

  public FileStorageRegistry(List<FileStorageProvider> providers) {
    this.providers = providers;
    this.defaultProvider =
        providers.stream()
            .filter(p -> p instanceof LocalStorageProvider)
            .findFirst()
            .orElseGet(() -> providers.isEmpty() ? null : providers.getFirst());
  }

  public FileStorageProvider getDefault() {
    if (defaultProvider == null) {
      throw new IllegalStateException("No FileStorageProvider configured");
    }
    return defaultProvider;
  }

  public FileStorageProvider get(String name) {
    return providers.stream()
        .filter(p -> p.getClass().getSimpleName().toLowerCase().contains(name.toLowerCase()))
        .findFirst()
        .orElseGet(this::getDefault);
  }
}
