package com.lambrk.saathi.notification.provider;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailProvider {
  private static final Logger log = LoggerFactory.getLogger(EmailProvider.class);

  private final JavaMailSender mailSender;

  public EmailProvider(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void send(String email, String subject, String body) {
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException("Email address is required");
    }
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
      helper.setTo(email);
      helper.setSubject(subject);
      helper.setText(body, true);
      mailSender.send(message);
      log.info("Email sent to {} subject={}", email, subject);
    } catch (Exception e) {
      log.error("Failed to send email to {}: {}", email, e.getMessage());
    }
  }
}
