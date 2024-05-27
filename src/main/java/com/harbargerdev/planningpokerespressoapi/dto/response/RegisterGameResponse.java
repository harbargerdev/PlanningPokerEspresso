package com.harbargerdev.planningpokerespressoapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterGameResponse {

    private String gameId;

    private String gameDisplayName;

    private String ownerId;

    private String ownerDisplayName;
}
