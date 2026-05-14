package com.lambrk.saathi.task.strategy;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NearestPartnerAssignmentStrategy implements AssignmentStrategy {
    @Override
    public Optional<Long> assignPartner(Long taskId) {
        return Optional.empty();
    }
}
