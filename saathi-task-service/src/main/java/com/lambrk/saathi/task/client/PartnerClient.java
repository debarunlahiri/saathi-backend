package com.lambrk.saathi.task.client;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PartnerClient {
    public List<Long> findAvailablePartners(double latitude, double longitude, double radiusKm) {
        return List.of();
    }
}
