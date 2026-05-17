package com.lambrk.saathi.admin.client;

import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PartnerClient {
  private final RestClient restClient;

  public PartnerClient(RestClient.Builder restClientBuilder) {
    this.restClient = restClientBuilder.baseUrl("http://saathi-partner-service").build();
  }

  public long pendingPartners() {
    Map<String, Object> response =
        restClient.get().uri("/api/partners/admin/counts/pending-kyc").retrieve().body(Map.class);
    return Long.parseLong(response.get("data").toString());
  }
}
