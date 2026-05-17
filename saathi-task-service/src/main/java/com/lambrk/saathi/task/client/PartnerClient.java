package com.lambrk.saathi.task.client;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PartnerClient {
  private final RestClient restClient;

  public PartnerClient(RestClient.Builder restClientBuilder) {
    this.restClient = restClientBuilder.baseUrl("http://saathi-partner-service").build();
  }

  public void ensurePartnerCanAccept(Long partnerId) {
    Map<String, Object> response =
        restClient.get().uri("/api/partners/{partnerId}", partnerId).retrieve().body(Map.class);
    Map<String, Object> partner = data(response);
    if (!"APPROVED".equals(partner.get("kycStatus"))) {
      throw new IllegalStateException("Partner KYC must be approved before accepting tasks");
    }
    if (!"ONLINE".equals(partner.get("availabilityStatus"))) {
      throw new IllegalStateException("Partner must be online before accepting tasks");
    }
  }

  public List<Map<String, Object>> availablePartners() {
    Map<String, Object> response =
        restClient.get().uri("/api/partners/available").retrieve().body(Map.class);
    return (List<Map<String, Object>>) data(response);
  }

  private Map<String, Object> data(Map<String, Object> response) {
    return (Map<String, Object>) response.get("data");
  }
}
