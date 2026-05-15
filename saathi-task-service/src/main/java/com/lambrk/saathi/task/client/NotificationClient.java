package com.lambrk.saathi.task.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class NotificationClient {
    private final RestClient restClient;

    public NotificationClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("http://saathi-notification-service").build();
    }

    public void notifyUser(Long userId, String title, String message, String type, String referenceId) {
        restClient.post()
                .uri("/api/notifications")
                .body(Map.of(
                        "userId", userId,
                        "title", title,
                        "message", message,
                        "notificationType", type,
                        "referenceId", referenceId
                ))
                .retrieve()
                .toBodilessEntity();
    }
}
