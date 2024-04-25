package com.harbargerdev.planningpokerespresso.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Vote {
    
    @Id
    @GeneratedValue
    private UUID voteId;

    @ManyToOne
    @JoinColumn(name = "cardid")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "playerid")
    private Player player;

    private int score;

    // Getters and setters

    public UUID getVoteId() {
        return voteId;
    }

    public void setVoteId(UUID voteId) {
        this.voteId = voteId;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
