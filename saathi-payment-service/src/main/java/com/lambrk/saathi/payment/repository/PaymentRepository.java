package com.lambrk.saathi.payment.repository;

import com.lambrk.saathi.payment.entity.Payment;
import com.lambrk.saathi.payment.enums.PaymentStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
  List<Payment> findByTaskIdOrderByIdDesc(Long taskId);

  long countByPaymentStatus(PaymentStatus paymentStatus);
}
