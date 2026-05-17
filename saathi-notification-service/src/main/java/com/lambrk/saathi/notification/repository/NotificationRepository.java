package com.lambrk.saathi.notification.repository;

import com.lambrk.saathi.notification.entity.Notification;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
  List<Notification> findByUserIdOrderByIdDesc(UUID userId);
}
