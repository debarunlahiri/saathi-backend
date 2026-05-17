package com.lambrk.saathi.identity.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String mobileNumber, @NotBlank String password) {}
