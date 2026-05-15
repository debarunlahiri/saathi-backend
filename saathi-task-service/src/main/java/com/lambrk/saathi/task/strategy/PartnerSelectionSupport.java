package com.lambrk.saathi.task.strategy;

import com.lambrk.saathi.task.entity.Task;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

final class PartnerSelectionSupport {
    private PartnerSelectionSupport() {
    }

    static Optional<Long> firstAvailable(List<Map<String, Object>> partners) {
        return partners.stream()
                .map(PartnerSelectionSupport::partnerId)
                .flatMap(Optional::stream)
                .findFirst();
    }

    static Optional<Long> nearest(Task task, List<Map<String, Object>> partners) {
        BigDecimal taskLatitude = task.getPickupLatitude();
        BigDecimal taskLongitude = task.getPickupLongitude();
        if (taskLatitude == null || taskLongitude == null) {
            return firstAvailable(partners);
        }

        return partners.stream()
                .map(partner -> candidate(taskLatitude.doubleValue(), taskLongitude.doubleValue(), partner))
                .flatMap(Optional::stream)
                .filter(Candidate::insideServiceRadius)
                .min(Comparator.comparingDouble(Candidate::distanceKm))
                .map(Candidate::partnerId);
    }

    static Optional<Long> highestRated(Task task, List<Map<String, Object>> partners) {
        BigDecimal taskLatitude = task.getPickupLatitude();
        BigDecimal taskLongitude = task.getPickupLongitude();

        return partners.stream()
                .filter(partner -> taskLatitude == null || taskLongitude == null
                        || candidate(taskLatitude.doubleValue(), taskLongitude.doubleValue(), partner)
                        .map(Candidate::insideServiceRadius)
                        .orElse(false))
                .max(Comparator
                        .comparingDouble(PartnerSelectionSupport::rating)
                        .thenComparingDouble(partner -> -distanceOrMax(task, partner)))
                .flatMap(PartnerSelectionSupport::partnerId);
    }

    private static Optional<Candidate> candidate(double taskLatitude, double taskLongitude, Map<String, Object> partner) {
        Optional<Long> partnerId = partnerId(partner);
        Optional<Double> partnerLatitude = decimal(partner.get("currentLatitude"));
        Optional<Double> partnerLongitude = decimal(partner.get("currentLongitude"));
        if (partnerId.isEmpty() || partnerLatitude.isEmpty() || partnerLongitude.isEmpty()) {
            return Optional.empty();
        }
        double distanceKm = distanceKm(taskLatitude, taskLongitude, partnerLatitude.get(), partnerLongitude.get());
        double serviceRadiusKm = decimal(partner.get("serviceRadiusKm")).orElse(3D);
        return Optional.of(new Candidate(partnerId.get(), distanceKm, serviceRadiusKm));
    }

    private static Optional<Long> partnerId(Map<String, Object> partner) {
        Object id = partner.get("id");
        if (id instanceof Number number) {
            return Optional.of(number.longValue());
        }
        if (id instanceof String value && !value.isBlank()) {
            try {
                return Optional.of(Long.parseLong(value));
            } catch (NumberFormatException ignored) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    private static double rating(Map<String, Object> partner) {
        return decimal(partner.get("averageRating")).orElse(0D);
    }

    private static double distanceOrMax(Task task, Map<String, Object> partner) {
        if (task.getPickupLatitude() == null || task.getPickupLongitude() == null) {
            return Double.MAX_VALUE;
        }
        return candidate(task.getPickupLatitude().doubleValue(), task.getPickupLongitude().doubleValue(), partner)
                .map(Candidate::distanceKm)
                .orElse(Double.MAX_VALUE);
    }

    private static Optional<Double> decimal(Object value) {
        if (value instanceof Number number) {
            return Optional.of(number.doubleValue());
        }
        if (value instanceof String text && !text.isBlank()) {
            try {
                return Optional.of(Double.parseDouble(text));
            } catch (NumberFormatException ignored) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    private static double distanceKm(double fromLatitude, double fromLongitude, double toLatitude, double toLongitude) {
        double earthRadiusKm = 6371D;
        double latitudeDelta = Math.toRadians(toLatitude - fromLatitude);
        double longitudeDelta = Math.toRadians(toLongitude - fromLongitude);
        double a = Math.sin(latitudeDelta / 2) * Math.sin(latitudeDelta / 2)
                + Math.cos(Math.toRadians(fromLatitude)) * Math.cos(Math.toRadians(toLatitude))
                * Math.sin(longitudeDelta / 2) * Math.sin(longitudeDelta / 2);
        return earthRadiusKm * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    private record Candidate(Long partnerId, double distanceKm, double serviceRadiusKm) {
        boolean insideServiceRadius() {
            return distanceKm <= serviceRadiusKm;
        }
    }
}
