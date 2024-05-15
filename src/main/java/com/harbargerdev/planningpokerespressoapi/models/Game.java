package com.harbargerdev.planningpokerespressoapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    @Setter
    private String gameId;

    @Getter
    @Setter
    private String displayName;

    @Getter
    @Setter
    private LocalDateTime startTime;

    @OneToOne(optional = true)
    @JoinColumn(name = "ownerid")
    @Getter
    @Setter
    private Player gameOwner;

    @Getter
    @OneToMany
    private List<Player> players;

    @Getter
    @OneToMany
    private List<Card> cards;

    @Getter
    @Setter
    @OneToOne(optional = true)
    @JoinColumn(name = "currentcardid")
    private Card currentCard;

    public Game() {
        players = new ArrayList<>();
        cards = new ArrayList<>();
    }
}
