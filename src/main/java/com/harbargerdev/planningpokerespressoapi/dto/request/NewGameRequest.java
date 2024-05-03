package com.harbargerdev.planningpokerespressoapi.dto.request;

import java.util.UUID;

public class NewGameRequest {
    
    private String displayName;

    private UUID gameOwnerId;

    public String getDisplayName() {
        return displayName;
    }

    public UUID getGameOwnerId() {
        return gameOwnerId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setGameOwnerId(UUID gameOwnerId) {
        this.gameOwnerId = gameOwnerId;
    }
}
