package com.harbargerdev.planningpokerespressoapi.services;

import com.harbargerdev.planningpokerespressoapi.repositories.GameRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

public class GameServiceTests {
    
    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerRepository playerRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
}
