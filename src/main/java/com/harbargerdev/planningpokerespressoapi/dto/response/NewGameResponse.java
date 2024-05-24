package com.harbargerdev.planningpokerespressoapi.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.harbargerdev.planningpokerespressoapi.models.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
public class NewGameResponse {
    
    @Setter
    private String gameId;

    @Setter
    private String displayName;

    @Setter
    private LocalDateTime startTime;

    @Setter
    private Player gameOwner;

    private final List<Player> players;

    public NewGameResponse() {
        players = new ArrayList<>();
    }
}
