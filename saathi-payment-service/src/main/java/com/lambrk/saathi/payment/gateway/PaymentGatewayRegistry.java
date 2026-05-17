package com.lambrk.saathi.payment.gateway;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayRegistry {
  private final List<PaymentGateway> gateways;

  public PaymentGatewayRegistry(List<PaymentGateway> gateways) {
    this.gateways = gateways;
  }

  public PaymentGateway get(String name) {
    String gatewayName = name == null ? "RAZORPAY" : name;
    return gateways.stream()
        .filter(gateway -> gateway.name().equalsIgnoreCase(gatewayName))
        .findFirst()
        .orElseThrow(
            () -> new IllegalArgumentException("Unsupported payment gateway: " + gatewayName));
  }
}
