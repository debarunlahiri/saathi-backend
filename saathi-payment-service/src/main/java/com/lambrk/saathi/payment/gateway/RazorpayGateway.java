package com.lambrk.saathi.payment.gateway;

import org.springframework.stereotype.Component;

@Component
public class RazorpayGateway implements PaymentGateway {
    @Override
    public String name() {
        return "RAZORPAY";
    }

    @Override
    public String createOrder(String taskId, long amountInPaise) {
        return "razorpay_order_" + taskId + "_" + amountInPaise;
    }

    @Override
    public boolean verify(String orderId, String paymentId, String signature) {
        return signature != null && !signature.isBlank();
    }
}
