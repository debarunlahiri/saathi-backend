package com.lambrk.saathi.notification.controller;

import com.lambrk.saathi.notification.dto.ApiResponse;
import com.lambrk.saathi.notification.dto.CreateNotificationRequest;
import com.lambrk.saathi.notification.entity.Notification;
import com.lambrk.saathi.notification.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<Notification> create(@Valid @RequestBody CreateNotificationRequest request) {
        return ApiResponse.success("Notification created successfully", service.create(request));
    }

    @GetMapping("/users/{userId}")
    public ApiResponse<List<Notification>> byUser(@PathVariable Long userId) {
        return ApiResponse.success("Notifications fetched successfully", service.byUser(userId));
    }

    @PutMapping("/{id}/read")
    public ApiResponse<Notification> read(@PathVariable Long id) {
        return ApiResponse.success("Notification marked as read", service.read(id));
    }
}
