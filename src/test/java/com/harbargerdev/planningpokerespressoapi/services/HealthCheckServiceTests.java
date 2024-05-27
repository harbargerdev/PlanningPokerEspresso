package com.harbargerdev.planningpokerespressoapi.services;

import com.harbargerdev.planningpokerespressoapi.models.HealthCheckDependencyStatus;
import com.harbargerdev.planningpokerespressoapi.models.HealthCheckResult;
import com.harbargerdev.planningpokerespressoapi.repositories.CardRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.GameRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.PlayerRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.VoteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HealthCheckServiceTests {

    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private CardRepository cardRepository;

    @MockBean
    private PlayerRepository playerRepository;

    @MockBean
    private VoteRepository voteRepository;

    @Autowired
    private HealthCheckService healthCheckService;

    @Test
    public void checkHealth_GameRepositoryDown() {
        when(gameRepository.findAll()).thenThrow(new RuntimeException("Game repository is down"));

        HealthCheckResult healthCheckResult = healthCheckService.checkHealth();
        assertThat(healthCheckResult.getStatus()).isEqualTo(HealthCheckDependencyStatus.DOWN);
        assertThat(healthCheckResult.getMessage()).isEqualTo("Game repository is down");
    }

    @Test
    public void checkHealth_CardRepositoryDown() {
        when(gameRepository.findAll()).thenReturn(new ArrayList<>());
        when(cardRepository.findAll()).thenThrow(new RuntimeException("Card repository is down"));

        HealthCheckResult healthCheckResult = healthCheckService.checkHealth();
        assertThat(healthCheckResult.getStatus()).isEqualTo(HealthCheckDependencyStatus.DOWN);
        assertThat(healthCheckResult.getMessage()).isEqualTo("Card repository is down");
    }

    @Test
    public void checkHealth_PlayerRepositoryDown() {
        when(gameRepository.findAll()).thenReturn(new ArrayList<>());
        when(cardRepository.findAll()).thenReturn(new ArrayList<>());
        when(playerRepository.findAll()).thenThrow(new RuntimeException("Player repository is down"));

        HealthCheckResult healthCheckResult = healthCheckService.checkHealth();
        assertThat(healthCheckResult.getStatus()).isEqualTo(HealthCheckDependencyStatus.DOWN);
        assertThat(healthCheckResult.getMessage()).isEqualTo("Player repository is down");
    }

    @Test
    public void checkHealth_VoteRepositoryDown() {
        when(gameRepository.findAll()).thenReturn(new ArrayList<>());
        when(cardRepository.findAll()).thenReturn(new ArrayList<>());
        when(playerRepository.findAll()).thenReturn(new ArrayList<>());
        when(voteRepository.findAll()).thenThrow(new RuntimeException("Vote repository is down"));

        HealthCheckResult healthCheckResult = healthCheckService.checkHealth();
        assertThat(healthCheckResult.getStatus()).isEqualTo(HealthCheckDependencyStatus.DOWN);
        assertThat(healthCheckResult.getMessage()).isEqualTo("Vote repository is down");
    }

    @Test
    public void checkHealth_AllDependenciesUp() {
        when(gameRepository.findAll()).thenReturn(new ArrayList<>());
        when(cardRepository.findAll()).thenReturn(new ArrayList<>());
        when(playerRepository.findAll()).thenReturn(new ArrayList<>());
        when(voteRepository.findAll()).thenReturn(new ArrayList<>());

        HealthCheckResult healthCheckResult = healthCheckService.checkHealth();
        assertThat(healthCheckResult.getStatus()).isEqualTo(HealthCheckDependencyStatus.UP);
        assertThat(healthCheckResult.getMessage()).isEqualTo("All dependencies are up and running!");
    }
}