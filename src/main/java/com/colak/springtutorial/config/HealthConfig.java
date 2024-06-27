package com.colak.springtutorial.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class HealthConfig {

    // http://localhost:8081/actuator - Shows all endpoints
    // http://localhost:8081/actuator/health/ - Shows all health indicators
    // http://localhost:8081/actuator/health/random - Shows my custom health indicator
    @Bean
    public HealthIndicator randomHealthIndicator() {
        return () -> {
            double chance = ThreadLocalRandom.current().nextDouble();
            Health.Builder status = Health.up();
            if (chance > 0.9) {
                status = Health.down();
            }
            return status.withDetail("chance", chance).build();
        };
    }

    // @Bean
    // public HealthIndicator dataSourceHealthIndicator(DataSource dataSource) {
    //     return () -> {
    //         try (Connection connection = dataSource.getConnection()) {
    //             if (connection.isValid(1000)) {
    //                 return Health.up().withDetail("database", "up").build();
    //             } else {
    //                 return Health.down().withDetail("database", "down").build();
    //             }
    //         } catch (SQLException e) {
    //             return Health.down(e).build();
    //         }
    //     };
    // }
}
