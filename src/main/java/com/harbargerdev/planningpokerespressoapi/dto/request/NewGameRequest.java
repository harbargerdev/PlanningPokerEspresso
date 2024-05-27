package com.harbargerdev.planningpokerespressoapi.dto.request;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewGameRequest {
    
    private String displayName;

    private String gameOwnerId;
}
