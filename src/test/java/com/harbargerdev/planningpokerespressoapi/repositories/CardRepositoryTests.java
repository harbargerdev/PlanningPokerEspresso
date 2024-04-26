package com.harbargerdev.planningpokerespressoapi.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.harbargerdev.planningpokerespressoapi.models.Card;

@SpringBootTest
public class CardRepositoryTests extends RepositoryTestBase {

    @BeforeEach
    void setupAndSeedData() {
        seedPlayers();
        seedCards();
    }

    @Test
    void testGetAllCards() {
        List<Card> allCards = cardRepository.findAll();

        assertEquals(10, allCards.size());
        assertTrue(allCards.stream().allMatch(c -> c.getCardId() != null));
        assertTrue(allCards.stream().allMatch(c -> c.getDisplayName() != null));
        assertTrue(allCards.stream().allMatch(c -> c.getFinalEstimation() >= 0));
        assertTrue(allCards.stream().allMatch(c -> c.isLocked() || !c.isLocked()));
        assertTrue(allCards.stream().allMatch(c -> c.isDone() || !c.isDone()));
    }
}
