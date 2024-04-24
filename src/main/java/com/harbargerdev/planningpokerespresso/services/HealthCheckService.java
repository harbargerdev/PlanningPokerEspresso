package com.harbargerdev.planningpokerespresso.services;

import org.springframework.stereotype.Service;

import com.harbargerdev.planningpokerespresso.models.HealthCheckDependencyStatus;
import com.harbargerdev.planningpokerespresso.models.HealthCheckResult;

@Service
public class HealthCheckService {

    private final String AllDependenciesAreUpAndRunning = "All dependencies are up and running!";

    public HealthCheckResult checkHealth() {
        return new HealthCheckResult(HealthCheckDependencyStatus.UP, AllDependenciesAreUpAndRunning);
    }
}
