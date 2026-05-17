package com.lambrk.saathi.task.client;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PricingClient {
  private final RestClient restClient;

  public PricingClient(RestClient.Builder restClientBuilder) {
    this.restClient = restClientBuilder.baseUrl("http://saathi-pricing-service").build();
  }

  public BigDecimal estimate(UUID serviceCategoryId) {
    Map<String, Object> response =
        restClient
            .post()
            .uri("/api/pricing/estimate")
            .body(Map.of("serviceCategoryId", serviceCategoryId))
            .retrieve()
            .body(Map.class);
    Map<String, Object> data = (Map<String, Object>) response.get("data");
    Object finalPrice = data.get("finalPrice");
    return new BigDecimal(finalPrice.toString());
  }
}
