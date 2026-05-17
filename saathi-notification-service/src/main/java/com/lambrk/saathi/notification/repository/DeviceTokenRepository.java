package com.lambrk.saathi.notification.repository;

import com.lambrk.saathi.notification.entity.DeviceToken;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceTokenRepository extends JpaRepository<DeviceToken, Long> {
  List<DeviceToken> findByUserId(Long userId);

  Optional<DeviceToken> findByUserIdAndDeviceToken(Long userId, String deviceToken);

  void deleteByDeviceToken(String deviceToken);
}
