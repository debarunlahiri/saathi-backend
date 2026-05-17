package com.lambrk.saathi.file.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record RegisterFileRequest(
    UUID ownerUserId,
    String referenceType,
    String referenceId,
    @NotBlank String fileName,
    String contentType,
    @NotBlank String storageUrl) {}
