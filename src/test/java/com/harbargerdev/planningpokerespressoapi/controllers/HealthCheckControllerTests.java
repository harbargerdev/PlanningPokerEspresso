package com.harbargerdev.planningpokerespressoapi.controllers;

import com.harbargerdev.planningpokerespressoapi.controllers.HealthCheckController;
import com.harbargerdev.planningpokerespressoapi.models.HealthCheckDependencyStatus;
import com.harbargerdev.planningpokerespressoapi.models.HealthCheckResult;
import com.harbargerdev.planningpokerespressoapi.services.HealthCheckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(HealthCheckController.class)
public class HealthCheckControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HealthCheckService healthCheckService;

    @Test
    public void testHealthCheck() throws Exception {
        HealthCheckResult healthCheckResult = new HealthCheckResult(HealthCheckDependencyStatus.UP, "The application is running.");
        when(healthCheckService.checkHealth()).thenReturn(healthCheckResult);

        mockMvc.perform(get("/api/health")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("UP")))
                .andExpect(jsonPath("$.message", is(healthCheckResult.getMessage())));
    }
}
