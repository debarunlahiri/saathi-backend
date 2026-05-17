package com.lambrk.saathi.file.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CloudinaryStorageProvider implements FileStorageProvider {
  private static final Logger log = LoggerFactory.getLogger(CloudinaryStorageProvider.class);

  private final Cloudinary cloudinary;

  public CloudinaryStorageProvider(
      @Value("${saathi.file.cloudinary.cloud-name:}") String cloudName,
      @Value("${saathi.file.cloudinary.api-key:}") String apiKey,
      @Value("${saathi.file.cloudinary.api-secret:}") String apiSecret) {
    this.cloudinary =
        new Cloudinary(
            ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
  }

  @Override
  public String store(String objectKey, byte[] content) {
    String publicId = extractPublicId(objectKey);
    try {
      Map<?, ?> result =
          cloudinary
              .uploader()
              .upload(content, ObjectUtils.asMap("public_id", publicId, "resource_type", "auto"));
      String url = (String) result.get("secure_url");
      log.info("File stored in Cloudinary url={}", url);
      return url;
    } catch (IOException e) {
      throw new RuntimeException("Failed to upload to Cloudinary: " + objectKey, e);
    }
  }

  private String extractPublicId(String objectKey) {
    int dotIndex = objectKey.lastIndexOf('.');
    return dotIndex > 0 ? objectKey.substring(0, dotIndex) : objectKey;
  }
}
