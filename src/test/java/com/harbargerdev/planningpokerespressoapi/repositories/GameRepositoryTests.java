package com.harbargerdev.planningpokerespressoapi.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.harbargerdev.planningpokerespressoapi.models.Game;

@SpringBootTest
public class GameRepositoryTests extends RepositoryTestBase {
    
    @BeforeEach
    public void setupAndSeedData() {
        seedPlayers();
        seedCards();
        seedGames();
    }

    @Test
    public void testGetAllGames() {
        List<Game> allGames = gameRepository.findAll();

        assertEquals(1, allGames.size());
        assertTrue(allGames.stream().allMatch(g -> g.getGameId() != null));
        assertTrue(allGames.stream().allMatch(g -> g.getDisplayName() != null));
        assertTrue(allGames.stream().allMatch(g -> g.getStartTime() != null));        
        assertTrue(allGames.stream().allMatch(g -> g.getGameOwner() != null));
        assertTrue(allGames.stream().allMatch(g -> g.getPlayers() != null));
        assertTrue(allGames.stream().allMatch(g -> g.getCards() != null));
        assertTrue(allGames.stream().allMatch(g -> g.getCurrentCard() == null));
    }

    @AfterEach
    public void tearDown() {
        gameRepository.deleteAll();
        cardRepository.deleteAll();
        playerRepository.deleteAll();
    }
}
