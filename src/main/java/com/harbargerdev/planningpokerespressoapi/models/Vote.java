package com.harbargerdev.planningpokerespressoapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
public class Vote {
    
    @Id
    @GeneratedValue
    private UUID voteId;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @OneToOne
    @JoinColumn(name = "playerId")
    private Player player;

    private String estimate;
}
