package com.lambrk.saathi.notification.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailProvider {
    private static final Logger log = LoggerFactory.getLogger(EmailProvider.class);

    public void send(String email, String subject, String body) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email address is required");
        }
        log.info("Queued email to {} subject={}", email, subject);
    }
}
