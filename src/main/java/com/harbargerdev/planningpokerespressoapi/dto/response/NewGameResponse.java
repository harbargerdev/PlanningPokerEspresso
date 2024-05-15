package com.harbargerdev.planningpokerespressoapi.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.harbargerdev.planningpokerespressoapi.models.Player;

import lombok.Getter;
import lombok.Setter;

public class NewGameResponse {
    
    @Getter
    @Setter
    private UUID gameId;

    @Getter
    @Setter
    private String displayName;

    @Getter
    @Setter
    private LocalDateTime startTime;

    @Getter
    @Setter
    private Player gameOwner;

    @Getter
    private List<Player> players;

    public NewGameResponse() {
        players = new ArrayList<>();
    }
}
