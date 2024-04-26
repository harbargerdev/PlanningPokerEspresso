package com.harbargerdev.planningpokerespressoapi.repositories;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

import com.harbargerdev.planningpokerespressoapi.models.*;

public class RepositoryTestBase {
    
    @Autowired
    protected CardRepository cardRepository;
    
    @Autowired
    protected PlayerRepository playerRepository;

    @Autowired
    protected VoteRepository voteRepository;

    @Autowired
    protected GameRepository gameRepository;

    protected void seedPlayers() {
        for (int i = 0; i < 4; i++) {
            Player player = new Player();
            player.setPlayerId(new UUID(32, 1));
            player.setDisplayName("Player " + i);
            if (i == 0) {
                player.setPlayerType(PlayerType.PARTICIPANT);
            } else {
                player.setPlayerType(PlayerType.AUDIENCE);
            }
            player.setPlayerType(null);
            playerRepository.save(player);
        }
    }

    protected void seedCards() {        
        for (int i = 0; i < 10; i++) {
            Card card = new Card();
            card.setCardId(new UUID(32, 1));
            card.setDisplayName("Test Story " + i);
            card.setFinalEstimation(i * 2);
            card.setLocked(i > 0);
            card.setDone(i == 1);            
            cardRepository.save(card);    
        }
    }

    protected void seedGames() {
        Game game = new Game();
        game.setGameId(new UUID(32, 1));
        game.setDisplayName("Test Game");
        game.setStartTime(LocalDateTime.now());
        game.setCards(cardRepository.findAll());
        game.setPlayers(playerRepository.findAll());
        game.setGameOwner(playerRepository.findAll().get(0));
        gameRepository.save(game);
    }

    protected void seedVotes() {
        cardRepository.findAll().forEach(card -> {
            playerRepository.findAll().forEach(player -> {
                Vote vote = new Vote();
                vote.setVoteId(new UUID(32, 1));
                vote.setCard(card);
                vote.setPlayer(player);
                vote.setScore(5);
                voteRepository.save(vote);
            });
        });
    }
}
