package com.harbargerdev.planningpokerespresso.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HealthCheckResult {
    
    @JsonProperty("status")
    private HealthCheckDependencyStatus status;

    @JsonProperty("message")
    private String message;

    public HealthCheckResult() {
    }

    public HealthCheckResult(HealthCheckDependencyStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HealthCheckDependencyStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(HealthCheckDependencyStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
