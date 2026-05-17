package com.lambrk.saathi.identity.dto.response;

import com.lambrk.saathi.identity.enums.UserRole;
import java.util.UUID;

public record AuthResponse(UUID userId, UserRole role, String accessToken, String refreshToken) {}
