package com.lambrk.saathi.file.storage;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3StorageProvider implements FileStorageProvider {
  private static final Logger log = LoggerFactory.getLogger(S3StorageProvider.class);

  private final S3Client s3Client;
  private final String bucket;

  public S3StorageProvider(
      @Value("${saathi.file.s3.bucket:}") String bucket,
      @Value("${saathi.file.s3.region:ap-south-1}") String region) {
    this.bucket = bucket;
    this.s3Client = S3Client.builder().region(Region.of(region)).build();
  }

  @Override
  public String store(String objectKey, byte[] content) {
    String key = "uploads/" + UUID.randomUUID() + "_" + sanitize(objectKey);
    s3Client.putObject(
        PutObjectRequest.builder().bucket(bucket).key(key).build(), RequestBody.fromBytes(content));
    log.info("File stored in S3 bucket={} key={}", bucket, key);
    return "https://" + bucket + ".s3.amazonaws.com/" + key;
  }

  private String sanitize(String name) {
    return name.replaceAll("[^a-zA-Z0-9._-]", "_");
  }
}
