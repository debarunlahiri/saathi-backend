package com.lambrk.saathi.payment.gateway;

import org.springframework.stereotype.Component;

@Component
public class PaytmGateway implements PaymentGateway {
    @Override
    public String createOrder(String taskId, long amountInPaise) {
        return "paytm_order_placeholder_" + taskId;
    }

    @Override
    public boolean verify(String orderId, String paymentId, String signature) {
        return signature != null && !signature.isBlank();
    }
}
