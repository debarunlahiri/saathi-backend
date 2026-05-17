package com.lambrk.saathi.complaint.dto;

import com.lambrk.saathi.complaint.enums.ComplaintType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateComplaintRequest(
    Long taskId,
    @NotNull Long raisedBy,
    @NotNull ComplaintType complaintType,
    @NotBlank String description) {}
