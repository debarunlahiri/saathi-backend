package com.lambrk.saathi.task.client;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WalletClient {
  private final RestClient restClient;

  public WalletClient(RestClient.Builder restClientBuilder) {
    this.restClient = restClientBuilder.baseUrl("http://saathi-wallet-service").build();
  }

  public void createEarning(
      UUID taskId, UUID partnerId, BigDecimal grossAmount, BigDecimal platformCommission) {
    restClient
        .post()
        .uri("/api/wallets/earnings")
        .body(
            Map.of(
                "taskId", taskId,
                "partnerId", partnerId,
                "grossAmount", grossAmount,
                "platformCommission", platformCommission))
        .retrieve()
        .toBodilessEntity();
  }
}
