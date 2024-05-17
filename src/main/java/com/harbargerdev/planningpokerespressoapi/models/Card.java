package com.harbargerdev.planningpokerespressoapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Card {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    @Setter
    private String cardId;

    @Getter
    @Setter
    private String displayName;

    @Getter
    @Setter
    private String finalEstimate;

    @Getter
    @Setter
    private boolean isLocked;

    @Getter
    @Setter
    private boolean isDone;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "gameid")
    private Game game;

    @Getter
    @OneToMany
    private List<Vote> votes;

    public Card() {
        votes = new ArrayList<>();
    }
}
