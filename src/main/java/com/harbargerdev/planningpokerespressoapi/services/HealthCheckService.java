package com.harbargerdev.planningpokerespressoapi.services;

import org.springframework.stereotype.Service;

import com.harbargerdev.planningpokerespressoapi.models.HealthCheckDependencyStatus;
import com.harbargerdev.planningpokerespressoapi.models.HealthCheckResult;

@Service
public class HealthCheckService {

    private final String AllDependenciesAreUpAndRunning = "All dependencies are up and running!";

    public HealthCheckResult checkHealth() {
        return new HealthCheckResult(HealthCheckDependencyStatus.UP, AllDependenciesAreUpAndRunning);
    }
}
