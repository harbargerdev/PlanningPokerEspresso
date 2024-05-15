package com.harbargerdev.planningpokerespressoapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class HealthCheckResult {
    
    @Getter
    @Setter
    @JsonProperty("status")
    private HealthCheckDependencyStatus status;

    @Getter
    @Setter
    @JsonProperty("message")
    private String message;

    public HealthCheckResult() {
    }

    public HealthCheckResult(HealthCheckDependencyStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
