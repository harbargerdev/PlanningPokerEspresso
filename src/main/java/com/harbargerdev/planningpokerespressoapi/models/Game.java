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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter
    private String gameId;

    @Setter
    private String displayName;

    @Setter
    private LocalDateTime startTime;

    @OneToOne(optional = true)
    @JoinColumn(name = "ownerid")
    @Setter
    private Player gameOwner;

    @OneToMany(mappedBy = "player")
    private List<Player> players;

    @OneToMany(mappedBy = "card")
    private List<Card> cards;

    @Setter
    @OneToOne(optional = true)
    @JoinColumn(name = "currentcardid")
    private Card currentCard;

    public Game() {
        players = new ArrayList<>();
        cards = new ArrayList<>();
    }
}
