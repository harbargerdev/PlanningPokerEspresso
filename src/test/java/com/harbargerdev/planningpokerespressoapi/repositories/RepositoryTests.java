package com.harbargerdev.planningpokerespressoapi.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    
    @BeforeEach
    public void setupAndSeedData() {

        Game game = new Game();
        game.setDisplayName("Test Game");
        game.setStartTime(LocalDateTime.now());
        
        game = gameRepository.save(game);
        System.out.println("Game ID: " + game.getGameId());
        
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Player player = new Player();
            player.setDisplayName("Player " + i);
            player.setPlayerType(i == 0 ? PlayerType.AUDIENCE : PlayerType.PARTICIPANT);
            player.setGame(game);            
            player = playerRepository.save(player);
            players.add(player);
            if (i == 0) {
                game.setGameOwner(player);
            }
        }
        
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Card card = new Card();
            card.setDisplayName("Test Story " + i);
            card.setFinalEstimate("5");
            card.setLocked(i != 4);
            card.setDone(i < 4);
            card.setGame(game);    
            cards.add(card);
            card = cardRepository.save(card);
            if (i == 4) {
                game.setCurrentCard(card);
            }
        }

        cardRepository.saveAllAndFlush(cards);

        List<Vote> votes = new ArrayList<>();

        cards.forEach(card -> {
            if (!card.isLocked()) {
                players.forEach(player -> {
                    Vote vote = new Vote();
                    vote.setCard(card);
                    vote.setPlayer(player);
                    vote.setEstimate("5");;
                    vote = voteRepository.save(vote);
                    votes.add(vote);
                });
            }            
        });
        
        gameRepository.saveAndFlush(game);
    }
    
    @Test
    public void testAllGetters() {

        List<Game> allGames = gameRepository.findAll();
        List<Player> allPlayers = playerRepository.findAll();
        List<Card> allCards = cardRepository.findAll();
        List<Vote> allVotes = voteRepository.findAll();
        
        assertEquals(1, allGames.size());
        assertTrue(allGames.stream().allMatch(g -> g.getGameId() != null));
        assertTrue(allGames.stream().allMatch(g -> g.getDisplayName() != null));
        assertTrue(allGames.stream().allMatch(g -> g.getStartTime() != null));        
        assertTrue(allGames.stream().allMatch(g -> g.getGameOwner() != null));
        assertTrue(allGames.stream().allMatch(g -> g.getPlayers() != null));
        assertTrue(allGames.stream().allMatch(g -> g.getCards() != null));
        assertTrue(allGames.stream().allMatch(g -> g.getCurrentCard() != null));
        
        assertEquals(4, allPlayers.size());
        assertTrue(allPlayers.stream().allMatch(p -> p.getPlayerId() != null));
        assertTrue(allPlayers.stream().allMatch(p -> p.getDisplayName() != null));
        assertTrue(allPlayers.stream().allMatch(p -> p.getPlayerType() != null));

        assertEquals(10, allCards.size());
        assertTrue(allCards.stream().allMatch(c -> c.getCardId() != null));
        assertTrue(allCards.stream().allMatch(c -> c.getDisplayName() != null));
        assertTrue(allCards.stream().allMatch(c -> c.getFinalEstimate() != null));
        assertTrue(allCards.stream().allMatch(c -> c.isLocked() || !c.isLocked()));
        assertTrue(allCards.stream().allMatch(c -> c.isDone() || !c.isDone()));
        assertTrue(allCards.stream().allMatch(c -> c.getGame() != null));

        assertEquals(40, allVotes.size());
        assertTrue(allVotes.stream().allMatch(v -> v.getVoteId() != null));
        assertTrue(allVotes.stream().allMatch(v -> v.getCard() != null));
        assertTrue(allVotes.stream().allMatch(v -> v.getPlayer() != null));
        assertTrue(allVotes.stream().allMatch(v -> v.getEstimate() != null));
    }

    @AfterEach
    public void tearDown() {
        cardRepository.findAll().forEach(card -> {
            cardRepository.save(card);
        });

        voteRepository.deleteAll();

        gameRepository.findAll().forEach(game -> {
            game.setGameOwner(null);
            gameRepository.save(game);
        });

        cardRepository.deleteAll();
        playerRepository.deleteAll();
        gameRepository.deleteAll();
    }
}
