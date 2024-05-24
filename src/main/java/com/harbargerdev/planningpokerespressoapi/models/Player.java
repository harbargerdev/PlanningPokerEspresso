package com.harbargerdev.planningpokerespressoapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
public class Player {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String playerId;

    private String displayName;

    @Enumerated(EnumType.STRING)
    private PlayerType playerType;

    @OneToOne
    @JoinColumn(name = "gameid")
    private Game game;
}
