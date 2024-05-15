package com.harbargerdev.planningpokerespressoapi.dto.request;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class NewGameRequest {
    
    @Getter
    @Setter
    private String displayName;

    @Getter
    @Setter
    private UUID gameOwnerId;
}
