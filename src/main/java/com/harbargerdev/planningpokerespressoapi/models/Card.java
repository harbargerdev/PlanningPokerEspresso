package com.harbargerdev.planningpokerespressoapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Card {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter
    private String cardId;

    @Setter
    private String displayName;

    @Setter
    private String finalEstimate;

    @Setter
    private boolean isLocked;

    @Setter
    private boolean isDone;

    @Setter
    @OneToOne
    @JoinColumn(name = "gameid")
    private Game game;

    @OneToMany
    private List<Vote> votes;

    public Card() {
        votes = new ArrayList<>();
    }
}
