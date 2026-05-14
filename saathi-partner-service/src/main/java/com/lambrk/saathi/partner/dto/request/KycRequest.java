package com.lambrk.saathi.partner.dto.request;

import jakarta.validation.constraints.NotBlank;

public record KycRequest(
        @NotBlank String aadhaarUrl,
        String panUrl,
        @NotBlank String addressProofUrl,
        @NotBlank String profilePhotoUrl,
        @NotBlank String bankAccountOrUpi
) {
}
