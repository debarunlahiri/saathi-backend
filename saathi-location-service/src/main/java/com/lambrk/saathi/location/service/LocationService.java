package com.lambrk.saathi.location.service;

import com.lambrk.saathi.location.dto.LocationUpdateRequest;
import com.lambrk.saathi.location.entity.LocationEvent;
import com.lambrk.saathi.location.repository.LocationEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationEventRepository repository;
    public LocationService(LocationEventRepository repository) { this.repository = repository; }
    public LocationEvent update(LocationUpdateRequest request) {
        LocationEvent event = new LocationEvent();
        event.setTaskId(request.taskId());
        event.setPartnerId(request.partnerId());
        event.setLatitude(request.latitude());
        event.setLongitude(request.longitude());
        event.setAccuracy(request.accuracy());
        return repository.save(event);
    }
    public LocationEvent latest(Long taskId) { return repository.findFirstByTaskIdOrderByCreatedAtDesc(taskId).orElseThrow(); }
    public List<LocationEvent> history(Long taskId) { return repository.findByTaskIdOrderByCreatedAtDesc(taskId); }
}
