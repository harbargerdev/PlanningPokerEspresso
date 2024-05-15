package com.harbargerdev.planningpokerespressoapi.dto.response;

import com.harbargerdev.planningpokerespressoapi.models.Game;
import com.harbargerdev.planningpokerespressoapi.models.Player;

import lombok.Getter;
import lombok.Setter;

public class RegisterGameResponse {
    
    @Getter
    @Setter
    private Game game;

    @Getter
    @Setter
    private Player owner;
}
