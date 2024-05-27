package com.harbargerdev.planningpokerespressoapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
public class Card {
    
    @Id
    @GeneratedValue
    @Setter
    private UUID cardId;

    @Setter
    private String displayName;

    @Setter
    private String finalEstimate;

    @Setter
    private boolean isLocked;

    @Setter
    private boolean isDone;

    @Setter
    @ManyToOne
    @JoinColumn(name = "gameId", nullable = false)
    private Game game;

    @OneToMany(mappedBy = "card")
    private final List<Vote> votes;

    public Card() {
        votes = new ArrayList<>();
    }
}
