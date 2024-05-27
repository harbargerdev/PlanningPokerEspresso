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
    @GeneratedValue
    @Column(name = "player_id")
    private UUID playerId;

    @Column(name = "display_name")
    private String displayName;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_type")
    private PlayerType playerType;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}
