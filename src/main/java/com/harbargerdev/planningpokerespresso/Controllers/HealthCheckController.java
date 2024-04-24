package com.harbargerdev.planningpokerespresso.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harbargerdev.planningpokerespresso.models.HealthCheckResult;
import com.harbargerdev.planningpokerespresso.services.HealthCheckService;

@RestController
@RequestMapping("/api")
public class HealthCheckController {
    
    private final HealthCheckService healthCheckService;

    public HealthCheckController(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("/health")
    public HealthCheckResult healthCheck() {
        return healthCheckService.checkHealth();
    }
}
