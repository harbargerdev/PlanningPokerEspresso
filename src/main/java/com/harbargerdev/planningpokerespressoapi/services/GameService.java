package com.harbargerdev.planningpokerespressoapi.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbargerdev.planningpokerespressoapi.dto.request.NewGameRequest;
import com.harbargerdev.planningpokerespressoapi.dto.response.NewGameResponse;
import com.harbargerdev.planningpokerespressoapi.models.Game;
import com.harbargerdev.planningpokerespressoapi.repositories.GameRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.PlayerRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gamesRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public NewGameResponse createNewGame(NewGameRequest request) {
        var gameOwner = playerRepository.findById(request.getGameOwnerId());
        
        if (gameOwner.isEmpty()) {
            throw new IllegalArgumentException("Player not found");
        }

        Game game = new Game();
        game.setDisplayName(request.getDisplayName());
        game.setStartTime(LocalDateTime.now());
        game.setGameOwner(gameOwner.get());
        game.getPlayers().add(gameOwner.get());

        gamesRepository.save(game);

        NewGameResponse response = new NewGameResponse();
        response.setGameId(game.getGameId());
        response.setDisplayName(game.getDisplayName());
        response.setStartTime(game.getStartTime());

        return response;
    }
}
