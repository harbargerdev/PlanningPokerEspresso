package com.harbargerdev.planningpokerespressoapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
public class Player {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    @Setter
    private String playerId;

    @Getter
    @Setter
    private String displayName;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private PlayerType playerType;

    @OneToOne
    @JoinColumn(name = "gameid")
    @Getter
    @Setter
    private Game game;
}
