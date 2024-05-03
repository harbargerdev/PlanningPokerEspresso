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

    @Test
    public void testCreateNewGame() {
        Player player = new Player();
        player.setPlayerId(UUID.randomUUID());

        when(playerRepository.findById(player.getPlayerId())).thenReturn(Optional.of(player));

        Game game = new Game();
        game.setGameId(UUID.randomUUID());
        game.setDisplayName("Test Game");
        game.setStartTime(LocalDateTime.now());
        game.setGameOwner(player);
        game.getPlayers().add(player);

        when(gameRepository.save(any(Game.class))).thenReturn(game);

        NewGameRequest request = new NewGameRequest();
        request.setDisplayName("Test Game");
        request.setGameOwnerId(player.getPlayerId());

        NewGameResponse response = gameService.createNewGame(request);

        assertEquals(game.getGameId(), response.getGameId());
        assertEquals(game.getDisplayName(), response.getDisplayName());
        assertEquals(game.getStartTime(), response.getStartTime());
        
        verify(playerRepository, times(1)).findById(player.getPlayerId());
        verify(gameRepository, times(1)).save(any(Game.class));
    }

    @Test
    public void testCreateNewGame_PlayerNotFound() {
        when(playerRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        NewGameRequest request = new NewGameRequest();
        request.setDisplayName("Test Game");
        request.setGameOwnerId(UUID.randomUUID());

        assertThrows(IllegalArgumentException.class, () -> gameService.createNewGame(request));
        
        verify(playerRepository, times(1)).findById(any(UUID.class));
        verify(gameRepository, never()).save(any(Game.class));
    }
}
