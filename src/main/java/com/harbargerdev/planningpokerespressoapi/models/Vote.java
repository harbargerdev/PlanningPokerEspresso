package com.harbargerdev.planningpokerespressoapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity(name = "vote")
public class Vote {
    
    @Id
    @GeneratedValue
    @Column(name = "vote_id")
    private UUID voteId;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "estimate")
    private String estimate;
}
