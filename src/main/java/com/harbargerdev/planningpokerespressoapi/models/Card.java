package com.harbargerdev.planningpokerespressoapi.models;

import jakarta.persistence.*;
import java.util.UUID;
import java.util.List;

@Entity
public class Card {
    
    @Id
    @GeneratedValue
    private UUID cardId;

    private String displayName;

    private int finalEstimate;

    private boolean isLocked;

    private boolean isDone;

    @OneToMany
    private List<Vote> votes;

    // Getters and setters
    public UUID getCardId() {
        return cardId;
    }

    public void setCardId(UUID cardId) {
        this.cardId = cardId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getFinalEstimation() {
        return finalEstimate;
    }

    public void setFinalEstimation(int finalEstimate) {
        this.finalEstimate = finalEstimate;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
