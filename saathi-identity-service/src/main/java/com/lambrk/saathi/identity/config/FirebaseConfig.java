package com.lambrk.saathi.identity.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class FirebaseConfig {

  @Bean
  FirebaseApp firebaseApp(@Value("${firebase.service-account.path:}") Resource serviceAccount)
      throws IOException {
    if (!serviceAccount.exists()) {
      throw new IllegalStateException(
          "Firebase service-account JSON not found at "
              + serviceAccount.getDescription()
              + ". Set firebase.service-account.path in config or place the file on the classpath.");
    }
    FirebaseOptions options =
        FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
            .build();
    return FirebaseApp.initializeApp(options);
  }
}
