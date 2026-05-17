package com.lambrk.saathi.payment.gateway;

public interface PaymentGateway {
  String name();

  String createOrder(String taskId, long amountInPaise);

  boolean verify(String orderId, String paymentId, String signature);
}
