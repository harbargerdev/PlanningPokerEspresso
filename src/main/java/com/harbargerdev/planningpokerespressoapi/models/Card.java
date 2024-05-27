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
    @Column(name = "card_id")
    private UUID cardId;

    @Setter
    @Column(name = "display_name")
    private String displayName;

    @Setter
    @Column(name = "final_estimate")
    private String finalEstimate;

    @Setter
    @Column(name = "is_locked")
    private boolean isLocked;

    @Setter
    @Column(name = "is_done")
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
