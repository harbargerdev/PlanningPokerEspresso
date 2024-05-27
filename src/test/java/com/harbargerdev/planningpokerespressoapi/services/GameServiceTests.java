package com.harbargerdev.planningpokerespressoapi.services;

import com.harbargerdev.planningpokerespressoapi.dto.request.RegisterGameRequest;
import com.harbargerdev.planningpokerespressoapi.dto.response.RegisterGameResponse;
import com.harbargerdev.planningpokerespressoapi.models.Game;
import com.harbargerdev.planningpokerespressoapi.models.Player;
import com.harbargerdev.planningpokerespressoapi.models.PlayerType;
import com.harbargerdev.planningpokerespressoapi.repositories.GameRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
    public void registerNewGame_GameRepositoryThrowsException_ExceptionPropagates() {
        RegisterGameRequest request = new RegisterGameRequest();
        request.setGameDisplayName("Test Game");
        request.setOwnerDisplayName("Test Owner");
        request.setOwnerPlayerType("CAST");

        when(gameRepository.save(any(Game.class))).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> gameService.registerNewGame(request));
    }

    @Test
    public void registerNewGame_UnsuccessfulCastPlayer_ThrowsException() {
        RegisterGameRequest request = new RegisterGameRequest();
        request.setGameDisplayName("Test Game");
        request.setOwnerDisplayName("Test Owner");
        request.setOwnerPlayerType("INVALID");

        Game inprogressGame = new Game();
        inprogressGame.setGameId(UUID.randomUUID());
        inprogressGame.setDisplayName("Test Game");

        when(gameRepository.save(any(Game.class))).thenReturn(inprogressGame);
        Mockito.doNothing().when(gameRepository).deleteById(inprogressGame.getGameId());

        assertThrows(Exception.class, () -> gameService.registerNewGame(request));
        Mockito.verify(gameRepository, Mockito.times(1)).deleteById(inprogressGame.getGameId());
    }

    @Test
    public void registerNewGame_SuccessfulSave_ReturnsResponse() {
        // Arrange
        RegisterGameRequest request = new RegisterGameRequest();
        request.setGameDisplayName("Test Game");
        request.setOwnerDisplayName("Test Owner");
        request.setOwnerPlayerType("AUDIENCE");

        Game inprogressGame = new Game();
        inprogressGame.setGameId(UUID.randomUUID());
        inprogressGame.setDisplayName("Test Game");

        when(gameRepository.save(any(Game.class))).thenReturn(inprogressGame);

        Player owner = new Player();
        owner.setPlayerId(UUID.randomUUID());
        owner.setDisplayName("Test Owner");
        owner.setPlayerType(PlayerType.PARTICIPANT);

        when(playerRepository.save(any(Player.class))).thenReturn(owner);

        // Act
        RegisterGameResponse response = gameService.registerNewGame(request);

        // Assert
        assert(response.getGameId() != null);
        assert(response.getGameDisplayName().equals("Test Game"));
        assert(response.getOwnerId() != null);
        assert(response.getOwnerDisplayName().equals("Test Owner"));
        Mockito.verify(gameRepository, Mockito.times(2)).save(any(Game.class));
        Mockito.verify(playerRepository, Mockito.times(1)).save(any(Player.class));
    }
}
