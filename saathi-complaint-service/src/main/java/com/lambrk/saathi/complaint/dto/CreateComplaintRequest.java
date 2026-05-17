package com.lambrk.saathi.complaint.dto;

import com.lambrk.saathi.complaint.enums.ComplaintType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreateComplaintRequest(
    UUID taskId,
    @NotNull UUID raisedBy,
    @NotNull ComplaintType complaintType,
    @NotBlank String description) {}
