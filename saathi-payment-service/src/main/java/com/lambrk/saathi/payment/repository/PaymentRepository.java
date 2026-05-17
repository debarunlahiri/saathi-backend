package com.lambrk.saathi.payment.repository;

import com.lambrk.saathi.payment.entity.Payment;
import com.lambrk.saathi.payment.enums.PaymentStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
  List<Payment> findByTaskIdOrderByIdDesc(UUID taskId);

  long countByPaymentStatus(PaymentStatus paymentStatus);
}
