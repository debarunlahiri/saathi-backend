package com.lambrk.saathi.location.repository;

import com.lambrk.saathi.location.entity.LocationEvent;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationEventRepository extends JpaRepository<LocationEvent, UUID> {
  Optional<LocationEvent> findFirstByTaskIdOrderByCreatedAtDesc(UUID taskId);

  List<LocationEvent> findByTaskIdOrderByCreatedAtDesc(UUID taskId);
}
