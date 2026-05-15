package com.lambrk.saathi.admin.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class PaymentClient {
    private final RestClient restClient;

    public PaymentClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("http://saathi-payment-service").build();
    }

    public long paymentIssues() {
        Map<String, Object> response = restClient.get()
                .uri("/api/payments/admin/counts/failed")
                .retrieve()
                .body(Map.class);
        return Long.parseLong(response.get("data").toString());
    }
}
