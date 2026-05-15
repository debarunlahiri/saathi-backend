package com.lambrk.saathi.notification.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SmsProvider {
    private static final Logger log = LoggerFactory.getLogger(SmsProvider.class);

    public void send(String mobileNumber, String message) {
        if (mobileNumber == null || mobileNumber.isBlank()) {
            throw new IllegalArgumentException("Mobile number is required");
        }
        log.info("Queued SMS to {}", mask(mobileNumber));
    }

    private String mask(String mobileNumber) {
        return mobileNumber.length() <= 4 ? "****" : "******" + mobileNumber.substring(mobileNumber.length() - 4);
    }
}
