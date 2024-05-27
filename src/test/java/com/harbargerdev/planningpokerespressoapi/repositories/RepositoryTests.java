package com.harbargerdev.planningpokerespressoapi.repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.harbargerdev.planningpokerespressoapi.models.Card;
import com.harbargerdev.planningpokerespressoapi.models.Game;
import com.harbargerdev.planningpokerespressoapi.models.Player;
import com.harbargerdev.planningpokerespressoapi.models.PlayerType;
import com.harbargerdev.planningpokerespressoapi.models.Vote;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RepositoryTests {

    @Autowired
    private CardRepository cardRepository;
    
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void registerGameValidation() {
        // Setup & Seed Data
        Game game = new Game();
        game.setDisplayName("Test Game");
        game.setStartTime(LocalDateTime.now());

        gameRepository.save(game);

        Player player = new Player();
        player.setGame(game);
        player.setPlayerType(PlayerType.PARTICIPANT);
        player.setDisplayName("Test Player");

        playerRepository.save(player);

        game.setGameOwner(player);
        gameRepository.save(game);

        // Retrieve Data
        Game savedGame = gameRepository.findById(game.getGameId()).get();

        // Check Data
        assertEquals(game.getGameId(), savedGame.getGameId());
        assertEquals(game.getDisplayName(), savedGame.getDisplayName());
        assertNotNull(savedGame.getStartTime());
        assertEquals(game.getGameOwner().getPlayerId(), savedGame.getGameOwner().getPlayerId());
        assertEquals(game.getGameOwner().getDisplayName(), savedGame.getGameOwner().getDisplayName());
        assertEquals(game.getGameOwner().getPlayerType(), savedGame.getGameOwner().getPlayerType());
    }
}
