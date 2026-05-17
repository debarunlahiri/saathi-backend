package com.lambrk.saathi.task.client;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PaymentClient {
  private final RestClient restClient;

  public PaymentClient(RestClient.Builder restClientBuilder) {
    this.restClient = restClientBuilder.baseUrl("http://saathi-payment-service").build();
  }

  public void createOrder(UUID taskId, UUID customerId, BigDecimal amount) {
    restClient
        .post()
        .uri("/api/payments/create-order")
        .body(Map.of("taskId", taskId, "customerId", customerId, "amount", amount))
        .retrieve()
        .toBodilessEntity();
  }
}
