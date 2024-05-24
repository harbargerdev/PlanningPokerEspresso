package com.harbargerdev.planningpokerespressoapi.dto.response;

import com.harbargerdev.planningpokerespressoapi.models.Game;
import com.harbargerdev.planningpokerespressoapi.models.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterGameResponse {

    private Game game;

    private Player owner;
}
