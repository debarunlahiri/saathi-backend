package com.lambrk.saathi.notification.repository;

import com.lambrk.saathi.notification.entity.DeviceToken;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceTokenRepository extends JpaRepository<DeviceToken, UUID> {
  List<DeviceToken> findByUserId(UUID userId);

  Optional<DeviceToken> findByUserIdAndDeviceToken(UUID userId, String deviceToken);

  void deleteByDeviceToken(String deviceToken);
}
