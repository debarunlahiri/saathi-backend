package com.lambrk.saathi.notification.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FirebasePushProvider {
    private static final Logger log = LoggerFactory.getLogger(FirebasePushProvider.class);

    public void send(String token, String title, String body) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("Push notification token is required");
        }
        log.info("Queued push notification to token ending with {} title={}", tokenSuffix(token), title);
    }

    private String tokenSuffix(String token) {
        return token.length() <= 6 ? token : token.substring(token.length() - 6);
    }
}
