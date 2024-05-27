package com.harbargerdev.planningpokerespressoapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity(name = "game")
public class Game {
    
    @Id
    @GeneratedValue
    @Setter
    @Column(name = "game_id")
    private UUID gameId;

    @Setter
    @Column(name = "display_name")
    private String displayName;

    @Setter
    @Column(name = "start_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime;

    @OneToOne
    @JoinColumn(name = "owner_id")
    @Setter
    private Player gameOwner;

    @OneToMany(mappedBy = "game")
    private final List<Player> players;

    @OneToMany(mappedBy = "game")
    private final List<Card> cards;

    @Setter
    @OneToOne
    @JoinColumn(name = "current_card_id")
    private Card currentCard;

    public Game() {
        players = new ArrayList<>();
        cards = new ArrayList<>();
    }
}
