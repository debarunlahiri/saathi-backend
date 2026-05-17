package com.lambrk.saathi.identity.dto.response;

import com.lambrk.saathi.identity.enums.UserRole;

public record AuthResponse(Long userId, UserRole role, String accessToken, String refreshToken) {}
