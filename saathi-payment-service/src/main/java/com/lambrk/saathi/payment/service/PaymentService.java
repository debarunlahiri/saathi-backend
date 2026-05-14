package com.lambrk.saathi.payment.service;

import com.lambrk.saathi.payment.dto.CreatePaymentOrderRequest;
import com.lambrk.saathi.payment.dto.VerifyPaymentRequest;
import com.lambrk.saathi.payment.entity.Payment;
import com.lambrk.saathi.payment.enums.PaymentStatus;
import com.lambrk.saathi.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository repository;
    public PaymentService(PaymentRepository repository) { this.repository = repository; }
    public Payment createOrder(CreatePaymentOrderRequest request) {
        Payment payment = new Payment();
        payment.setTaskId(request.taskId());
        payment.setCustomerId(request.customerId());
        payment.setAmount(request.amount());
        payment.setPaymentGateway(request.paymentGateway() == null ? "RAZORPAY" : request.paymentGateway());
        payment.setGatewayOrderId("order_" + UUID.randomUUID());
        return repository.save(payment);
    }
    @Transactional public Payment verify(VerifyPaymentRequest request) {
        Payment payment = repository.findById(request.paymentId()).orElseThrow();
        payment.setGatewayPaymentId(request.gatewayPaymentId());
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        payment.setPaidAt(Instant.now());
        return payment;
    }
    public List<Payment> byTask(Long taskId) { return repository.findByTaskIdOrderByIdDesc(taskId); }
}
