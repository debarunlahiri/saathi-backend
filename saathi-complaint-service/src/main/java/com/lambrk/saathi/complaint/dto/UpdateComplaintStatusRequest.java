package com.lambrk.saathi.complaint.dto;

import com.lambrk.saathi.complaint.enums.ComplaintStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateComplaintStatusRequest(@NotNull ComplaintStatus status, String adminRemarks) {}
