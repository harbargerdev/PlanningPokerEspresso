package com.harbargerdev.planningpokerespressoapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterPlayerResponse {

    private String gameId;

    private String playerId;

    private String playerDisplayName;

    private String playerType;

}
