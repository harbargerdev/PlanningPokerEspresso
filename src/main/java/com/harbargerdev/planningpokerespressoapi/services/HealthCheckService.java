package com.harbargerdev.planningpokerespressoapi.services;

import com.harbargerdev.planningpokerespressoapi.repositories.CardRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.GameRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.PlayerRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbargerdev.planningpokerespressoapi.models.HealthCheckDependencyStatus;
import com.harbargerdev.planningpokerespressoapi.models.HealthCheckResult;

@Service
public class HealthCheckService {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckService.class);

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private VoteRepository voteRepository;

    public HealthCheckResult checkHealth() {

        try{
            gameRepository.findAll();
            cardRepository.findAll();
            playerRepository.findAll();
            voteRepository.findAll();
        } catch (Exception e) {
            logger.error("Error checking health of dependencies", e);
            return new HealthCheckResult(HealthCheckDependencyStatus.DOWN, "Database connection error.");
        }

        return new HealthCheckResult(HealthCheckDependencyStatus.UP, "All dependencies are up and running!");
    }
}
