package com.lambrk.saathi.identity.client;

import com.lambrk.saathi.identity.entity.User;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ProfileProvisioningClient {
  private final RestClient customerClient;
  private final RestClient partnerClient;

  public ProfileProvisioningClient(RestClient.Builder restClientBuilder) {
    this.customerClient = restClientBuilder.baseUrl("http://saathi-customer-service").build();
    this.partnerClient = restClientBuilder.baseUrl("http://saathi-partner-service").build();
  }

  public void createCustomerProfile(User user) {
    customerClient
        .post()
        .uri("/api/customers")
        .body(profilePayload(user))
        .retrieve()
        .toBodilessEntity();
  }

  public void createPartnerProfile(User user) {
    partnerClient
        .post()
        .uri("/api/partners")
        .body(profilePayload(user))
        .retrieve()
        .toBodilessEntity();
  }

  private Map<String, Object> profilePayload(User user) {
    Map<String, Object> payload = new HashMap<>();
    payload.put("identityUserId", user.getId());
    payload.put("fullName", user.getFullName());
    payload.put("mobileNumber", user.getMobileNumber());
    payload.put("email", user.getEmail());
    return payload;
  }
}
