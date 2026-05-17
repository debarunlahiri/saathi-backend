package com.lambrk.saathi.admin.client;

import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ComplaintClient {
  private final RestClient restClient;

  public ComplaintClient(RestClient.Builder restClientBuilder) {
    this.restClient = restClientBuilder.baseUrl("http://saathi-complaint-service").build();
  }

  public long openComplaints() {
    Map<String, Object> response =
        restClient.get().uri("/api/complaints/admin/counts/open").retrieve().body(Map.class);
    return Long.parseLong(response.get("data").toString());
  }
}
