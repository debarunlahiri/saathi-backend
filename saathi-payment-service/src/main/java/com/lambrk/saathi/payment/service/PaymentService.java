package com.lambrk.saathi.payment.service;

import com.lambrk.saathi.payment.dto.CreatePaymentOrderRequest;
import com.lambrk.saathi.payment.dto.VerifyPaymentRequest;
import com.lambrk.saathi.payment.entity.Payment;
import com.lambrk.saathi.payment.enums.PaymentStatus;
import com.lambrk.saathi.payment.gateway.PaymentGateway;
import com.lambrk.saathi.payment.gateway.PaymentGatewayRegistry;
import com.lambrk.saathi.payment.repository.PaymentRepository;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {
  private final PaymentRepository repository;
  private final PaymentGatewayRegistry gatewayRegistry;

  public PaymentService(PaymentRepository repository, PaymentGatewayRegistry gatewayRegistry) {
    this.repository = repository;
    this.gatewayRegistry = gatewayRegistry;
  }

  public Payment createOrder(CreatePaymentOrderRequest request) {
    String gatewayName = request.paymentGateway() == null ? "RAZORPAY" : request.paymentGateway();
    PaymentGateway gateway = gatewayRegistry.get(gatewayName);
    long amountInPaise =
        request.amount().movePointRight(2).setScale(0, RoundingMode.HALF_UP).longValueExact();
    Payment payment = new Payment();
    payment.setTaskId(request.taskId());
    payment.setCustomerId(request.customerId());
    payment.setAmount(request.amount());
    payment.setPaymentGateway(gateway.name());
    payment.setGatewayOrderId(gateway.createOrder(request.taskId().toString(), amountInPaise));
    return repository.save(payment);
  }

  @Transactional
  public Payment verify(VerifyPaymentRequest request) {
    Payment payment = repository.findById(request.paymentId()).orElseThrow();
    PaymentGateway gateway = gatewayRegistry.get(payment.getPaymentGateway());
    if (!gateway.verify(
        payment.getGatewayOrderId(), request.gatewayPaymentId(), request.signature())) {
      payment.setPaymentStatus(PaymentStatus.FAILED);
      throw new IllegalArgumentException("Invalid payment signature");
    }
    payment.setGatewayPaymentId(request.gatewayPaymentId());
    payment.setPaymentStatus(PaymentStatus.SUCCESS);
    payment.setPaidAt(Instant.now());
    return payment;
  }

  public List<Payment> byTask(Long taskId) {
    return repository.findByTaskIdOrderByIdDesc(taskId);
  }

  public long failedCount() {
    return repository.countByPaymentStatus(PaymentStatus.FAILED);
  }
}
