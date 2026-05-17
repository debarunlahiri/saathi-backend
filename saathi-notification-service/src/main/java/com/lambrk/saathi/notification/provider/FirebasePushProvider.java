package com.lambrk.saathi.notification.provider;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FirebasePushProvider {
  private static final Logger log = LoggerFactory.getLogger(FirebasePushProvider.class);

  private final FirebaseMessaging firebaseMessaging;

  public FirebasePushProvider(FirebaseMessaging firebaseMessaging) {
    this.firebaseMessaging = firebaseMessaging;
  }

  public void send(String token, String title, String body) {
    if (token == null || token.isBlank()) {
      throw new IllegalArgumentException("Push notification token is required");
    }
    try {
      Message message =
          Message.builder()
              .setToken(token)
              .setNotification(Notification.builder().setTitle(title).setBody(body).build())
              .build();
      String response = firebaseMessaging.send(message);
      log.info(
          "Push notification sent successfully. token={} messageId={}",
          tokenSuffix(token),
          response);
    } catch (Exception e) {
      log.error(
          "Failed to send push notification to token={}: {}", tokenSuffix(token), e.getMessage());
    }
  }

  private String tokenSuffix(String token) {
    return token.length() <= 6 ? token : token.substring(token.length() - 6);
  }
}
