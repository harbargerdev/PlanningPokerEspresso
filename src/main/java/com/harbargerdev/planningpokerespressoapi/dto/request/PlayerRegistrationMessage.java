package com.harbargerdev.planningpokerespressoapi.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PlayerRegistrationMessage {

    private UUID playerId;

    private UUID gameId;
}
