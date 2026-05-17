package com.lambrk.saathi.admin.client;

import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class TaskClient {
  private final RestClient restClient;

  public TaskClient(RestClient.Builder restClientBuilder) {
    this.restClient = restClientBuilder.baseUrl("http://saathi-task-service").build();
  }

  public long activeTasks() {
    return count("/api/tasks/admin/counts/active");
  }

  private long count(String uri) {
    Map<String, Object> response = restClient.get().uri(uri).retrieve().body(Map.class);
    return Long.parseLong(response.get("data").toString());
  }
}
