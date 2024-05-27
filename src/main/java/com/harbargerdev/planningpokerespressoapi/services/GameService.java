package com.harbargerdev.planningpokerespressoapi.services;

import java.time.LocalDateTime;
import java.util.UUID;

import com.harbargerdev.planningpokerespressoapi.dto.response.RegisterGameResponse;
import com.harbargerdev.planningpokerespressoapi.models.Player;
import com.harbargerdev.planningpokerespressoapi.models.PlayerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbargerdev.planningpokerespressoapi.dto.request.RegisterGameRequest;
import com.harbargerdev.planningpokerespressoapi.models.Game;
import com.harbargerdev.planningpokerespressoapi.repositories.GameRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.PlayerRepository;

@Service
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private GameRepository gamesRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public RegisterGameResponse registerNewGame(RegisterGameRequest request) {

        RegisterGameResponse response = new RegisterGameResponse();

        try {
            Game game = new Game();
            game.setDisplayName(request.getGameDisplayName());
            game.setStartTime(LocalDateTime.now());
            Game persistedGame = gamesRepository.save(game);

            response.setGameId(persistedGame.getGameId().toString());
            response.setGameDisplayName(persistedGame.getDisplayName());

            Player owner = new Player();
            owner.setDisplayName(request.getOwnerDisplayName());
            owner.setPlayerType(PlayerType.valueOf(request.getOwnerPlayerType()));
            owner.setGame(persistedGame);
            Player persistedOwner = playerRepository.save(owner);

            game.setGameOwner(persistedOwner);
            gamesRepository.save(persistedGame);

            response.setOwnerId(persistedOwner.getPlayerId().toString());
            response.setOwnerDisplayName(persistedOwner.getDisplayName());

            return response;
        } catch (Exception ex) {
            logger.error("Error registering new game", ex);

            if (response.getGameId() != null) {
                gamesRepository.deleteById(UUID.fromString(response.getGameId()));
            }

            throw ex;
        }
    }
}
