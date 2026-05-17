package com.lambrk.saathi.notification.provider;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SmsProvider {
  private static final Logger log = LoggerFactory.getLogger(SmsProvider.class);

  private final RestClient restClient;
  private final String apiKey;
  private final String senderId;

  public SmsProvider(
      @Qualifier("plainRestClientBuilder") RestClient.Builder restClientBuilder,
      @Value("${sms.api-url:}") String apiUrl,
      @Value("${sms.api-key:}") String apiKey,
      @Value("${sms.sender-id:SAATHI}") String senderId) {
    this.restClient = restClientBuilder.baseUrl(apiUrl).build();
    this.apiKey = apiKey;
    this.senderId = senderId;
  }

  public void send(String mobileNumber, String message) {
    if (mobileNumber == null || mobileNumber.isBlank()) {
      throw new IllegalArgumentException("Mobile number is required");
    }
    if (apiKey.isEmpty()) {
      log.info("SMS provider not configured. Queued SMS to {}", mask(mobileNumber));
      return;
    }
    try {
      Map<String, String> body =
          Map.of(
              "to", mobileNumber,
              "message", message,
              "sender", senderId);
      restClient
          .post()
          .contentType(MediaType.APPLICATION_JSON)
          .header("Authorization", "Bearer " + apiKey)
          .body(body)
          .retrieve()
          .toBodilessEntity();
      log.info("SMS sent to {}", mask(mobileNumber));
    } catch (Exception e) {
      log.error("Failed to send SMS to {}: {}", mask(mobileNumber), e.getMessage());
    }
  }

  private String mask(String mobileNumber) {
    return mobileNumber.length() <= 4
        ? "****"
        : "******" + mobileNumber.substring(mobileNumber.length() - 4);
  }
}
