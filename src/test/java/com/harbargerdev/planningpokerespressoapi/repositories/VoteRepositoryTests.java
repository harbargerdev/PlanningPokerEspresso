package com.harbargerdev.planningpokerespressoapi.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.harbargerdev.planningpokerespressoapi.models.Vote;

@SpringBootTest
public class VoteRepositoryTests extends RepositoryTestBase {

    @BeforeEach
    void setupAndSeedDate() {
        seedPlayers();
        seedCards();
        seedVotes();
    }

    @Test
    void testGetAllVotes() {
        List<Vote> allVotes = voteRepository.findAll();
        
        assertEquals(40, allVotes.size());
        assertTrue(allVotes.stream().allMatch(v -> v.getVoteId() != null));
        assertTrue(allVotes.stream().allMatch(v -> v.getCard() != null));
        assertTrue(allVotes.stream().allMatch(v -> v.getPlayer() != null));
        assertTrue(allVotes.stream().allMatch(v -> v.getScore() > 0));
    }

    @AfterEach
    void tearDown() {
        cardRepository.findAll().forEach(card -> {
            card.setVotes(null);
            cardRepository.save(card);
        });
        voteRepository.deleteAll();
        cardRepository.deleteAll();
        playerRepository.deleteAll();
    }
}
