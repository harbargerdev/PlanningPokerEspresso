package com.harbargerdev.planningpokerespressoapi.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Player {
    
    @Id
    @GeneratedValue
    private UUID playerId;

    private String displayName;

    @Enumerated(EnumType.STRING)
    private PlayerType playerType;

    // Getters and setters
    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
