package com.lambrk.saathi.file.controller;

import com.lambrk.saathi.file.dto.ApiResponse;
import com.lambrk.saathi.file.dto.RegisterFileRequest;
import com.lambrk.saathi.file.entity.FileAsset;
import com.lambrk.saathi.file.service.FileAssetService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {
    private final FileAssetService service;
    public FileController(FileAssetService service) { this.service = service; }
    @PostMapping public ApiResponse<FileAsset> register(@Valid @RequestBody RegisterFileRequest request) { return ApiResponse.success("File registered successfully", service.register(request)); }
    @GetMapping("/references/{referenceType}/{referenceId}") public ApiResponse<List<FileAsset>> byReference(@PathVariable String referenceType, @PathVariable String referenceId) { return ApiResponse.success("Files fetched successfully", service.byReference(referenceType, referenceId)); }
}
