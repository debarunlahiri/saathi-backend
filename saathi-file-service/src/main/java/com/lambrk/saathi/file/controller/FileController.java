package com.lambrk.saathi.file.controller;

import com.lambrk.saathi.file.dto.ApiResponse;
import com.lambrk.saathi.file.dto.RegisterFileRequest;
import com.lambrk.saathi.file.entity.FileAsset;
import com.lambrk.saathi.file.service.FileAssetService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {
  private final FileAssetService service;

  public FileController(FileAssetService service) {
    this.service = service;
  }

  @PostMapping
  public ApiResponse<FileAsset> register(@Valid @RequestBody RegisterFileRequest request) {
    return ApiResponse.success("File registered successfully", service.register(request));
  }

  @PostMapping("/upload")
  public ApiResponse<FileAsset> upload(
      @RequestParam(required = false) Long ownerUserId,
      @RequestParam(required = false) String referenceType,
      @RequestParam(required = false) String referenceId,
      @RequestParam("file") MultipartFile file)
      throws IOException {
    FileAsset asset =
        service.upload(
            ownerUserId,
            referenceType,
            referenceId,
            file.getOriginalFilename(),
            file.getContentType(),
            file.getBytes());
    return ApiResponse.success("File uploaded successfully", asset);
  }

  @GetMapping("/download/{fileName}")
  public ResponseEntity<Resource> download(@PathVariable String fileName) {
    FileAsset asset = service.download("/api/files/download/" + fileName);
    if (asset == null) {
      return ResponseEntity.notFound().build();
    }
    Path filePath = Paths.get(System.getProperty("user.dir"), "data", "files", fileName);
    Resource resource = new FileSystemResource(filePath);
    if (!resource.exists()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok()
        .contentType(
            MediaType.parseMediaType(
                asset.getContentType() != null
                    ? asset.getContentType()
                    : "application/octet-stream"))
        .header(
            HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + asset.getFileName() + "\"")
        .body(resource);
  }

  @GetMapping("/references/{referenceType}/{referenceId}")
  public ApiResponse<List<FileAsset>> byReference(
      @PathVariable String referenceType, @PathVariable String referenceId) {
    return ApiResponse.success(
        "Files fetched successfully", service.byReference(referenceType, referenceId));
  }
}
