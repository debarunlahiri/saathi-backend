package com.lambrk.saathi.payment.gateway;

import java.util.HexFormat;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RazorpayGateway implements PaymentGateway {

  private final RestClient restClient;
  private final String keyId;
  private final String keySecret;

  public RazorpayGateway(
      RestClient.Builder restClientBuilder,
      @Value("${razorpay.key-id:}") String keyId,
      @Value("${razorpay.key-secret:}") String keySecret) {
    this.restClient = restClientBuilder.baseUrl("https://api.razorpay.com/v1").build();
    this.keyId = keyId;
    this.keySecret = keySecret;
  }

  @Override
  public String name() {
    return "RAZORPAY";
  }

  @Override
  public String createOrder(String taskId, long amountInPaise) {
    Map<String, Object> body =
        Map.of("amount", amountInPaise, "currency", "INR", "receipt", "task_" + taskId);
    Map<?, ?> response =
        restClient
            .post()
            .uri("/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .headers(headers -> headers.setBasicAuth(keyId, keySecret))
            .body(body)
            .retrieve()
            .body(Map.class);
    return response != null ? (String) response.get("id") : null;
  }

  @Override
  public boolean verify(String orderId, String paymentId, String signature) {
    if (orderId == null || paymentId == null || signature == null) {
      return false;
    }
    String payload = orderId + "|" + paymentId;
    String expected = hmacSha256(payload, keySecret);
    return expected.equals(signature);
  }

  private String hmacSha256(String data, String secret) {
    try {
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(new SecretKeySpec(secret.getBytes(), "HmacSHA256"));
      return HexFormat.of().formatHex(mac.doFinal(data.getBytes()));
    } catch (Exception e) {
      throw new RuntimeException("HMAC computation failed", e);
    }
  }
}
