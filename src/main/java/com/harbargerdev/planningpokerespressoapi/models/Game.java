package com.harbargerdev.planningpokerespressoapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
public class Game {
    
    @Id
    @GeneratedValue
    @Setter
    private UUID gameId;

    @Setter
    private String displayName;

    @Setter
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime;

    @OneToOne
    @JoinColumn(name = "ownerId")
    @Setter
    private Player gameOwner;

    @OneToMany(mappedBy = "game")
    private final List<Player> players;

    @OneToMany(mappedBy = "game")
    private final List<Card> cards;

    @Setter
    @OneToOne
    @JoinColumn(name = "currentCardId")
    private Card currentCard;

    public Game() {
        players = new ArrayList<>();
        cards = new ArrayList<>();
    }
}
