package com.lambrk.saathi.payment.repository;

import com.lambrk.saathi.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByTaskIdOrderByIdDesc(Long taskId);
}
