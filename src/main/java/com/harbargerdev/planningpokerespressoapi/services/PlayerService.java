package com.harbargerdev.planningpokerespressoapi.services;

import com.harbargerdev.planningpokerespressoapi.dto.request.RegisterPlayerRequest;
import com.harbargerdev.planningpokerespressoapi.dto.response.RegisterPlayerResponse;
import com.harbargerdev.planningpokerespressoapi.models.Game;
import com.harbargerdev.planningpokerespressoapi.models.Player;
import com.harbargerdev.planningpokerespressoapi.models.PlayerType;
import com.harbargerdev.planningpokerespressoapi.repositories.GameRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.PlayerRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public RegisterPlayerResponse registerNewPlayer(RegisterPlayerRequest request) throws Exception {

        // Search for the Game from repository
        Game game = gameRepository.findById(UUID.fromString(request.getGameId())).orElseThrow();

        Player player = new Player();
        player.setDisplayName(request.getPlayerDisplayName());
        player.setPlayerType(PlayerType.valueOf(request.getPlayerType().toUpperCase()));
        player.setGame(game);

        Player persistedPlayer = playerRepository.save(player);

        game.getPlayers().add(persistedPlayer);

        Game persistedGame = gameRepository.save(game);

        RegisterPlayerResponse response = new RegisterPlayerResponse();
        response.setPlayerId(persistedPlayer.getPlayerId().toString());
        response.setPlayerDisplayName(persistedPlayer.getDisplayName());
        response.setPlayerType(persistedPlayer.getPlayerType().toString());
        response.setGameId(persistedGame.getGameId().toString());

        return response;
    }
}
