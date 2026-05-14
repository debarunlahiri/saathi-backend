package com.lambrk.saathi.location.repository;

import com.lambrk.saathi.location.entity.LocationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationEventRepository extends JpaRepository<LocationEvent, Long> {
    Optional<LocationEvent> findFirstByTaskIdOrderByCreatedAtDesc(Long taskId);
    List<LocationEvent> findByTaskIdOrderByCreatedAtDesc(Long taskId);
}
