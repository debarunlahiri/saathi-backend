package com.lambrk.saathi.notification.service;

import com.lambrk.saathi.notification.dto.CreateNotificationRequest;
import com.lambrk.saathi.notification.entity.Notification;
import com.lambrk.saathi.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public Notification create(CreateNotificationRequest request) {
        Notification notification = new Notification();
        notification.setUserId(request.userId());
        notification.setTitle(request.title());
        notification.setMessage(request.message());
        notification.setNotificationType(request.notificationType());
        notification.setReferenceId(request.referenceId());
        return repository.save(notification);
    }

    public List<Notification> byUser(Long userId) {
        return repository.findByUserIdOrderByIdDesc(userId);
    }

    @Transactional
    public Notification read(Long id) {
        Notification notification = repository.findById(id).orElseThrow();
        notification.setReadStatus(true);
        return notification;
    }
}
