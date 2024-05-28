package com.harbargerdev.planningpokerespressoapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterPlayerRequest {

    private String gameId;

    private String playerDisplayName;

    private String playerType;
}
