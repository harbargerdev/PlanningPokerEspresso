package com.harbargerdev.planningpokerespresso.services;

import com.harbargerdev.planningpokerespresso.models.HealthCheckDependencyStatus;
import com.harbargerdev.planningpokerespresso.models.HealthCheckResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HealthCheckServiceTests {

    @Autowired
    private HealthCheckService healthCheckService;

    @Test
    public void testHealthCheck() {
        HealthCheckResult healthCheckResult = healthCheckService.checkHealth();
        assertThat(healthCheckResult.getStatus()).isEqualTo(HealthCheckDependencyStatus.UP);
        assertThat(healthCheckResult.getMessage()).isEqualTo("All dependencies are up and running!");
    }
}