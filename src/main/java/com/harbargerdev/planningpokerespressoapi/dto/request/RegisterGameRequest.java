package com.harbargerdev.planningpokerespressoapi.dto.request;

import lombok.Getter;
import lombok.Setter;

public class RegisterGameRequest {
    
    @Getter
    @Setter
    private String gameDisplayName;

    @Getter
    @Setter
    private String ownerDisplayName;

    @Getter
    @Setter
    private String ownerPlayerType;
}
