package com.lambrk.saathi.notification.provider;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WhatsAppProvider {
  private static final Logger log = LoggerFactory.getLogger(WhatsAppProvider.class);

  private final RestClient restClient;
  private final String phoneNumberId;
  private final String accessToken;

  public WhatsAppProvider(
      RestClient.Builder restClientBuilder,
      @Value("${whatsapp.phone-number-id:}") String phoneNumberId,
      @Value("${whatsapp.access-token:}") String accessToken) {
    this.restClient = restClientBuilder.baseUrl("https://graph.facebook.com/v20.0").build();
    this.phoneNumberId = phoneNumberId;
    this.accessToken = accessToken;
  }

  public void send(String mobileNumber, String message) {
    if (mobileNumber == null || mobileNumber.isBlank()) {
      throw new IllegalArgumentException("Mobile number is required");
    }
    if (phoneNumberId.isEmpty() || accessToken.isEmpty()) {
      log.info(
          "WhatsApp provider not configured. Queued WhatsApp message to {}", mask(mobileNumber));
      return;
    }
    try {
      Map<String, Object> body =
          Map.of(
              "messaging_product",
              "whatsapp",
              "to",
              mobileNumber,
              "type",
              "text",
              "text",
              Map.of("body", message));
      restClient
          .post()
          .uri("/" + phoneNumberId + "/messages")
          .contentType(MediaType.APPLICATION_JSON)
          .header("Authorization", "Bearer " + accessToken)
          .body(body)
          .retrieve()
          .toBodilessEntity();
      log.info("WhatsApp message sent to {}", mask(mobileNumber));
    } catch (Exception e) {
      log.error("Failed to send WhatsApp message to {}: {}", mask(mobileNumber), e.getMessage());
    }
  }

  private String mask(String mobileNumber) {
    return mobileNumber.length() <= 4
        ? "****"
        : "******" + mobileNumber.substring(mobileNumber.length() - 4);
  }
}
