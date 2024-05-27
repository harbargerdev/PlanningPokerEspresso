package com.harbargerdev.planningpokerespressoapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterGameRequest {
    
    private String gameDisplayName;

    private String ownerDisplayName;

    private String ownerPlayerType;
}
