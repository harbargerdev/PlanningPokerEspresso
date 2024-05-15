package com.harbargerdev.planningpokerespressoapi.services;

import com.harbargerdev.planningpokerespressoapi.dto.request.NewGameRequest;
import com.harbargerdev.planningpokerespressoapi.dto.response.NewGameResponse;
import com.harbargerdev.planningpokerespressoapi.models.Game;
import com.harbargerdev.planningpokerespressoapi.models.Player;
import com.harbargerdev.planningpokerespressoapi.repositories.GameRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
