package com.harbargerdev.planningpokerespressoapi.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.harbargerdev.planningpokerespressoapi.models.Player;

@SpringBootTest
public class PlayerRepositoryTests extends RepositoryTestBase {

    @BeforeEach
    public void setupAndSeedData() {
        seedPlayers();
    }

    @Test
    public void testGetAllPlayers() {
        List<Player> allPlayers = playerRepository.findAll();

        assertEquals(4, allPlayers.size());
        assertTrue(allPlayers.stream().allMatch(p -> p.getPlayerId() != null));
        assertTrue(allPlayers.stream().allMatch(p -> p.getDisplayName() != null));
        assertTrue(allPlayers.stream().allMatch(p -> p.getPlayerType() != null));
    }

    @AfterEach
    public void tearDown() {
        playerRepository.deleteAll();
    }
}
