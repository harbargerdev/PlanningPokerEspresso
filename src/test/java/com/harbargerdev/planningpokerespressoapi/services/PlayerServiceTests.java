package com.harbargerdev.planningpokerespressoapi.services;

import com.harbargerdev.planningpokerespressoapi.dto.request.RegisterPlayerRequest;
import com.harbargerdev.planningpokerespressoapi.dto.response.RegisterPlayerResponse;
import com.harbargerdev.planningpokerespressoapi.models.Game;
import com.harbargerdev.planningpokerespressoapi.models.Player;
import com.harbargerdev.planningpokerespressoapi.models.PlayerType;
import com.harbargerdev.planningpokerespressoapi.repositories.GameRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PlayerServiceTests {

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Test
    public void registerNewPlayer_GameNotFound_ThrowsEntityNotFoundException() {

        RegisterPlayerRequest request = new RegisterPlayerRequest();
        request.setGameId("some-guid-id");
        request.setPlayerDisplayName("Test Player");
        request.setPlayerType("PARTICIPANT");

        when(gameRepository.findById(any(UUID.class))).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> playerService.registerNewPlayer(request));
    }

    @Test
    public void registerNewPlayer_UnsuccessfulCastPlayerType_ThrowsIllegalArgumentException() {
        RegisterPlayerRequest request = new RegisterPlayerRequest();
        request.setGameId("some-guid-id");
        request.setPlayerDisplayName("Test Player");
        request.setPlayerType("INVALID");

        assertThrows(IllegalArgumentException.class, () -> playerService.registerNewPlayer(request));
    }

    @Test
    public void registerNewPlayer_ValidRequest_Success() throws Exception {
        RegisterPlayerRequest request = new RegisterPlayerRequest();
        request.setGameId(UUID.randomUUID().toString());
        request.setPlayerDisplayName("Test Player");
        request.setPlayerType("PARTICIPANT");

        Game initialGame = new Game();
        initialGame.setGameId(UUID.randomUUID());
        initialGame.setDisplayName("Test Game");

        when(gameRepository.findById(any(UUID.class))).thenReturn(Optional.of(initialGame));

        Player persistedPlayer = new Player();
        persistedPlayer.setPlayerId(UUID.randomUUID());
        persistedPlayer.setDisplayName("Test Player");
        persistedPlayer.setPlayerType(PlayerType.PARTICIPANT);
        persistedPlayer.setGame(initialGame);

        when(playerRepository.save(any(Player.class))).thenReturn(persistedPlayer);

        Game persistedGame = new Game();
        persistedGame.setGameId(initialGame.getGameId());
        persistedGame.setDisplayName(initialGame.getDisplayName());
        persistedGame.getPlayers().add(persistedPlayer);

        when(gameRepository.save(any(Game.class))).thenReturn(persistedGame);

        RegisterPlayerResponse response = playerService.registerNewPlayer(request);

        assertNotNull(response);
        assertNotNull(response.getPlayerId());
        assertEquals(persistedGame.getGameId().toString(), response.getGameId());
        assertEquals(persistedPlayer.getPlayerId().toString(), response.getPlayerId());
        assertEquals(persistedPlayer.getDisplayName(), response.getPlayerDisplayName());
        assertEquals(persistedPlayer.getPlayerType().toString(), response.getPlayerType());
    }
}
