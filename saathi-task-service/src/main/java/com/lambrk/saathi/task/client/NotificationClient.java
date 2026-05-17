package com.lambrk.saathi.task.client;

import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NotificationClient {
  private final RestClient restClient;

  public NotificationClient(RestClient.Builder restClientBuilder) {
    this.restClient = restClientBuilder.baseUrl("http://saathi-notification-service").build();
  }

  public void notifyUser(
      UUID userId, String title, String message, String type, String referenceId) {
    restClient
        .post()
        .uri("/api/notifications")
        .body(
            Map.of(
                "userId", userId,
                "title", title,
                "message", message,
                "notificationType", type,
                "referenceId", referenceId))
        .retrieve()
        .toBodilessEntity();
  }
}
