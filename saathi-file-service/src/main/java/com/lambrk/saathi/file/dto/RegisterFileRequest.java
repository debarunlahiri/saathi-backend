package com.lambrk.saathi.file.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterFileRequest(Long ownerUserId, String referenceType, String referenceId, @NotBlank String fileName, String contentType, @NotBlank String storageUrl) {
}
