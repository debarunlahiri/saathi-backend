package com.lambrk.saathi.task.client;

import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ChatClient {
  private final RestClient restClient;

  public ChatClient(RestClient.Builder restClientBuilder) {
    this.restClient = restClientBuilder.baseUrl("http://saathi-chat-service").build();
  }

  public void createRoom(Long taskId, Long customerId, Long partnerId) {
    restClient
        .post()
        .uri("/api/chats/rooms")
        .body(Map.of("taskId", taskId, "customerId", customerId, "partnerId", partnerId))
        .retrieve()
        .toBodilessEntity();
  }
}
