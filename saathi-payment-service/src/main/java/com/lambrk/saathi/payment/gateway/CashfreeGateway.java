package com.lambrk.saathi.payment.gateway;

import java.util.Base64;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CashfreeGateway implements PaymentGateway {

  private final RestClient restClient;
  private final String appId;
  private final String secretKey;
  private final String baseUrl;

  public CashfreeGateway(
      RestClient.Builder restClientBuilder,
      @Value("${cashfree.app-id:}") String appId,
      @Value("${cashfree.secret-key:}") String secretKey,
      @Value("${cashfree.base-url:https://sandbox.cashfree.com}") String baseUrl) {
    this.restClient = restClientBuilder.baseUrl(baseUrl).build();
    this.appId = appId;
    this.secretKey = secretKey;
    this.baseUrl = baseUrl;
  }

  @Override
  public String name() {
    return "CASHFREE";
  }

  @Override
  public String createOrder(String taskId, long amountInPaise) {
    double amount = amountInPaise / 100.0;
    Map<String, Object> body =
        Map.of(
            "order_id",
            "order_" + taskId + "_" + System.currentTimeMillis(),
            "order_amount",
            amount,
            "order_currency",
            "INR",
            "order_note",
            "Task payment for " + taskId);

    Map<?, ?> response =
        restClient
            .post()
            .uri("/pg/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .header("x-api-version", "2023-08-01")
            .header("x-client-id", appId)
            .header("x-client-secret", secretKey)
            .body(body)
            .retrieve()
            .body(Map.class);

    if (response != null) {
      Object orderId = response.get("order_id");
      Object paymentSessionId = response.get("payment_session_id");
      if (orderId != null && paymentSessionId != null) {
        return orderId + ":" + paymentSessionId;
      }
      return orderId != null ? orderId.toString() : null;
    }
    return null;
  }

  @Override
  public boolean verify(String orderId, String paymentId, String signature) {
    if (orderId == null || paymentId == null || signature == null) {
      return false;
    }
    String payload = orderId + paymentId;
    String expected = hmacSha256(payload, secretKey);
    return expected.equals(signature);
  }

  private String hmacSha256(String data, String secret) {
    try {
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(new SecretKeySpec(secret.getBytes(), "HmacSHA256"));
      return Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes()));
    } catch (Exception e) {
      throw new RuntimeException("HMAC computation failed", e);
    }
  }
}
