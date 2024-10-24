package com.colak.springtutorial.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

// http://localhost:8080/actuator - Shows all endpoints
// http://localhost:8080/actuator/health/ - Shows all health indicators including custom health indicators
// http://localhost:8080/actuator/health/random - Shows only my custom health indicator
@Component
public class RandomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        double chance = ThreadLocalRandom.current().nextDouble();
        Health.Builder builder = Health.up();
        if (chance > 0.9) {
            builder = Health.down();
        }
        return builder.withDetail("chance", chance).build();
    }
}
