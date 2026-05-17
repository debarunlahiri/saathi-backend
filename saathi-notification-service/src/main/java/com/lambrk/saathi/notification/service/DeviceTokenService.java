package com.lambrk.saathi.notification.service;

import com.lambrk.saathi.notification.dto.RegisterDeviceTokenRequest;
import com.lambrk.saathi.notification.entity.DeviceToken;
import com.lambrk.saathi.notification.repository.DeviceTokenRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeviceTokenService {
  private final DeviceTokenRepository repository;

  public DeviceTokenService(DeviceTokenRepository repository) {
    this.repository = repository;
  }

  public void register(RegisterDeviceTokenRequest request) {
    if (repository
        .findByUserIdAndDeviceToken(request.userId(), request.deviceToken())
        .isPresent()) {
      return;
    }
    DeviceToken token = new DeviceToken();
    token.setUserId(request.userId());
    token.setDeviceToken(request.deviceToken());
    repository.save(token);
  }

  @Transactional
  public void unregister(RegisterDeviceTokenRequest request) {
    repository.deleteByDeviceToken(request.deviceToken());
  }

  public List<String> tokens(UUID userId) {
    return repository.findByUserId(userId).stream().map(DeviceToken::getDeviceToken).toList();
  }
}
