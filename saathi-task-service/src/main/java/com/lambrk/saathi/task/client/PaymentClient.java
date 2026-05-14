package com.lambrk.saathi.task.client;

import org.springframework.stereotype.Component;

@Component
public class PaymentClient {
    public String createOrder(Long taskId) {
        return "payment_order_placeholder_" + taskId;
    }
}
