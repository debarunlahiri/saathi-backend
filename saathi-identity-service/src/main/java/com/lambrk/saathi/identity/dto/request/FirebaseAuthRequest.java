package com.lambrk.saathi.identity.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FirebaseAuthRequest(@NotBlank String idToken, String fullName, String email) {}
