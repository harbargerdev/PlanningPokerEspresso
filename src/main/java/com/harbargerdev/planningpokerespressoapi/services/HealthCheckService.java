package com.harbargerdev.planningpokerespressoapi.services;

import com.harbargerdev.planningpokerespressoapi.repositories.CardRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.GameRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.PlayerRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbargerdev.planningpokerespressoapi.models.HealthCheckDependencyStatus;
import com.harbargerdev.planningpokerespressoapi.models.HealthCheckResult;

@Service
public class HealthCheckService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private VoteRepository voteRepository;

    private final String AllDependenciesAreUpAndRunning = "All dependencies are up and running!";

    public HealthCheckResult checkHealth() {

        try{
            gameRepository.findAll();
            cardRepository.findAll();
            playerRepository.findAll();
            voteRepository.findAll();
        } catch (Exception e) {
            return new HealthCheckResult(HealthCheckDependencyStatus.DOWN, e.getMessage());
        }

        return new HealthCheckResult(HealthCheckDependencyStatus.UP, AllDependenciesAreUpAndRunning);
    }
}
