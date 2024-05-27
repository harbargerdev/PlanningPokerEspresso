package com.harbargerdev.planningpokerespressoapi.services;

import java.time.LocalDateTime;

import com.harbargerdev.planningpokerespressoapi.dto.response.RegisterGameResponse;
import com.harbargerdev.planningpokerespressoapi.models.Player;
import com.harbargerdev.planningpokerespressoapi.models.PlayerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbargerdev.planningpokerespressoapi.dto.request.RegisterGameRequest;
import com.harbargerdev.planningpokerespressoapi.models.Game;
import com.harbargerdev.planningpokerespressoapi.repositories.GameRepository;
import com.harbargerdev.planningpokerespressoapi.repositories.PlayerRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gamesRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public RegisterGameResponse registerNewGame(RegisterGameRequest request) {
        try {
            Game game = new Game();
            game.setDisplayName(request.getGameDisplayName());
            game.setStartTime(LocalDateTime.now());
            gamesRepository.save(game);

            Player owner = new Player();
            owner.setDisplayName(request.getOwnerDisplayName());
            owner.setPlayerType(PlayerType.valueOf(request.getOwnerPlayerType()));
            owner.setGame(game);
            playerRepository.save(owner);

            game.setGameOwner(owner);
            gamesRepository.save(game);

            RegisterGameResponse response = new RegisterGameResponse();
            response.setGameId(game.getGameId().toString());
            response.setGameDisplayName(game.getDisplayName());
            response.setOwnerId(owner.getPlayerId().toString());
            response.setOwnerDisplayName(owner.getDisplayName());

            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
