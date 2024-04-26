package com.harbargerdev.planningpokerespressoapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harbargerdev.planningpokerespressoapi.services.HealthCheckService;
import com.harbargerdev.planningpokerespressoapi.models.HealthCheckResult;

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
