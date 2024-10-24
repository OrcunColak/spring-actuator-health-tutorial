package com.colak.springtutorial.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// http://localhost:8080/actuator/health/
// http://localhost:8080/actuator/health/remoteService
@Component
public class RemoteServiceHealthIndicator implements HealthIndicator {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String REMOTE_SERVICE_URL = "https://remote-service-url/health";

    @Override
    public Health health() {
        try {
            String response = restTemplate.getForObject(REMOTE_SERVICE_URL, String.class);
            if ("OK".equalsIgnoreCase(response)) {
                return Health.up().withDetail("RemoteService", "Available").build();
            } else {
                return Health.down().withDetail("RemoteService", "Unavailable").build();
            }
        } catch (Exception exception) {
            return Health.down(exception).withDetail("RemoteService", "Error").build();
        }
    }
}
