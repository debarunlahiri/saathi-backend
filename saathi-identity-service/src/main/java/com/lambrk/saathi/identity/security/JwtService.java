package com.lambrk.saathi.identity.security;

import com.lambrk.saathi.identity.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey key;
    private final long accessTokenMinutes;
    private final long refreshTokenDays;

    public JwtService(
            @Value("${saathi.jwt.secret:change-this-local-development-secret-key-change-this}") String secret,
            @Value("${saathi.jwt.access-token-minutes:120}") long accessTokenMinutes,
            @Value("${saathi.jwt.refresh-token-days:30}") long refreshTokenDays
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenMinutes = accessTokenMinutes;
        this.refreshTokenDays = refreshTokenDays;
    }

    public String accessToken(User user) {
        return token(user, accessTokenMinutes * 60);
    }

    public String refreshToken(User user) {
        return token(user, refreshTokenDays * 24 * 60 * 60);
    }

    public Long subject(String token) {
        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        return Long.valueOf(claims.getSubject());
    }

    private String token(User user, long seconds) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("role", user.getRole().name())
                .claim("mobileNumber", user.getMobileNumber())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(seconds)))
                .signWith(key)
                .compact();
    }
}
