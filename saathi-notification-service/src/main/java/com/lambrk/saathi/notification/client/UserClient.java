package com.lambrk.saathi.notification.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserClient {

  private final RestClient restClient;

  public UserClient(RestClient.Builder restClientBuilder) {
    this.restClient = restClientBuilder.baseUrl("http://saathi-identity-service").build();
  }

  public UserDto getUser(Long userId) {
    try {
      return restClient.get().uri("/api/users/{id}", userId).retrieve().body(UserDto.class);
    } catch (Exception e) {
      return null;
    }
  }

  public record UserDto(Long id, String fullName, String mobileNumber, String email) {}
}
