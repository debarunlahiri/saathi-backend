package com.lambrk.saathi.notification.service;

import com.lambrk.saathi.notification.client.UserClient;
import com.lambrk.saathi.notification.dto.CreateNotificationRequest;
import com.lambrk.saathi.notification.entity.Notification;
import com.lambrk.saathi.notification.provider.EmailProvider;
import com.lambrk.saathi.notification.provider.FirebasePushProvider;
import com.lambrk.saathi.notification.provider.SmsProvider;
import com.lambrk.saathi.notification.provider.WhatsAppProvider;
import com.lambrk.saathi.notification.repository.NotificationRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {
  private final NotificationRepository repository;
  private final FirebasePushProvider pushProvider;
  private final DeviceTokenService deviceTokenService;
  private final EmailProvider emailProvider;
  private final SmsProvider smsProvider;
  private final WhatsAppProvider whatsAppProvider;
  private final UserClient userClient;

  public NotificationService(
      NotificationRepository repository,
      FirebasePushProvider pushProvider,
      DeviceTokenService deviceTokenService,
      EmailProvider emailProvider,
      SmsProvider smsProvider,
      WhatsAppProvider whatsAppProvider,
      UserClient userClient) {
    this.repository = repository;
    this.pushProvider = pushProvider;
    this.deviceTokenService = deviceTokenService;
    this.emailProvider = emailProvider;
    this.smsProvider = smsProvider;
    this.whatsAppProvider = whatsAppProvider;
    this.userClient = userClient;
  }

  public Notification create(CreateNotificationRequest request) {
    Notification notification = new Notification();
    notification.setUserId(request.userId());
    notification.setTitle(request.title());
    notification.setMessage(request.message());
    notification.setNotificationType(request.notificationType());
    notification.setReferenceId(request.referenceId());
    notification = repository.save(notification);
    sendViaAllChannels(request.userId(), request.title(), request.message());
    return notification;
  }

  public List<Notification> byUser(UUID userId) {
    return repository.findByUserIdOrderByIdDesc(userId);
  }

  @Transactional
  public Notification read(UUID id) {
    Notification notification = repository.findById(id).orElseThrow();
    notification.setReadStatus(true);
    return notification;
  }

  public void sendPushToUser(UUID userId, String title, String body) {
    List<String> tokens = deviceTokenService.tokens(userId);
    for (String token : tokens) {
      pushProvider.send(token, title, body);
    }
  }

  private void sendViaAllChannels(UUID userId, String title, String body) {
    sendPushNotification(userId, title, body);
    UserClient.UserDto user = userClient.getUser(userId);
    if (user != null) {
      if (user.email() != null && !user.email().isBlank()) {
        emailProvider.send(user.email(), title, body);
      }
      if (user.mobileNumber() != null && !user.mobileNumber().isBlank()) {
        smsProvider.send(user.mobileNumber(), body);
        whatsAppProvider.send(user.mobileNumber(), body);
      }
    }
  }

  private void sendPushNotification(UUID userId, String title, String body) {
    List<String> tokens = deviceTokenService.tokens(userId);
    for (String token : tokens) {
      pushProvider.send(token, title, body);
    }
  }
}
