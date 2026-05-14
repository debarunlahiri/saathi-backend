package com.lambrk.saathi.payment.controller;

import com.lambrk.saathi.payment.dto.*;
import com.lambrk.saathi.payment.entity.Payment;
import com.lambrk.saathi.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/create-order")
    public ApiResponse<Payment> createOrder(@Valid @RequestBody CreatePaymentOrderRequest request) {
        return ApiResponse.success("Payment order created successfully", service.createOrder(request));
    }

    @PostMapping("/verify")
    public ApiResponse<Payment> verify(@Valid @RequestBody VerifyPaymentRequest request) {
        return ApiResponse.success("Payment verified successfully", service.verify(request));
    }

    @GetMapping("/task/{taskId}")
    public ApiResponse<List<Payment>> byTask(@PathVariable Long taskId) {
        return ApiResponse.success("Payments fetched successfully", service.byTask(taskId));
    }
}
