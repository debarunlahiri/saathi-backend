package com.lambrk.saathi.payment.gateway;

import java.util.HexFormat;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PaytmGateway implements PaymentGateway {

  private final RestClient restClient;
  private final String merchantId;
  private final String merchantKey;
  private final String baseUrl;

  public PaytmGateway(
      RestClient.Builder restClientBuilder,
      @Value("${paytm.merchant-id:}") String merchantId,
      @Value("${paytm.merchant-key:}") String merchantKey,
      @Value("${paytm.base-url:https://securegw-stage.paytm.in}") String baseUrl) {
    this.restClient = restClientBuilder.baseUrl(baseUrl).build();
    this.merchantId = merchantId;
    this.merchantKey = merchantKey;
    this.baseUrl = baseUrl;
  }

  @Override
  public String name() {
    return "PAYTM";
  }

  @Override
  public String createOrder(String taskId, long amountInPaise) {
    String orderId = "ORDER_" + taskId + "_" + System.currentTimeMillis();
    Map<String, String> body = new TreeMap<>();
    body.put("mid", merchantId);
    body.put("orderId", orderId);
    body.put("amount", String.valueOf(amountInPaise));
    body.put("txnAmount", String.valueOf(amountInPaise));
    body.put("userInfo", "CUST_" + taskId);
    String checksum = generateChecksum(body);
    body.put("checksumhash", checksum);

    Map<?, ?> response =
        restClient
            .post()
            .uri("/theia/api/v1/initiateTransaction")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Map.of("head", Map.of("signature", checksum), "body", body))
            .retrieve()
            .body(Map.class);

    if (response != null && response.containsKey("body")) {
      Map<?, ?> respBody = (Map<?, ?>) response.get("body");
      Object txnToken = respBody.get("txnToken");
      return txnToken != null ? orderId + ":" + txnToken : orderId;
    }
    return orderId;
  }

  @Override
  public boolean verify(String orderId, String paymentId, String signature) {
    if (orderId == null || paymentId == null || signature == null) {
      return false;
    }
    Map<String, String> params = new TreeMap<>();
    params.put("orderId", orderId);
    params.put("paymentId", paymentId);
    String expected = generateChecksum(params);
    return expected.equals(signature);
  }

  private String generateChecksum(Map<String, String> params) {
    StringBuilder data = new StringBuilder();
    for (Map.Entry<String, String> entry : params.entrySet()) {
      if (entry.getValue() != null) {
        data.append(entry.getValue()).append("|");
      }
    }
    data.append(merchantKey);
    return hmacSha256(data.toString());
  }

  private String hmacSha256(String data) {
    try {
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(new SecretKeySpec(merchantKey.getBytes(), "HmacSHA256"));
      return HexFormat.of().formatHex(mac.doFinal(data.getBytes()));
    } catch (Exception e) {
      throw new RuntimeException("HMAC computation failed", e);
    }
  }
}
