package com.lambrk.saathi.identity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank String fullName,
    @NotBlank String mobileNumber,
    String email,
    @NotBlank @Size(min = 6) String password) {}
