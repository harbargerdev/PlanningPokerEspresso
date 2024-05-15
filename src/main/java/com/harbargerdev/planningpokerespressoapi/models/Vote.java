package com.harbargerdev.planningpokerespressoapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
public class Vote {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    @Setter
    private String voteId;

    @ManyToOne
    @JoinColumn(name = "cardid")
    @Getter
    @Setter
    private Card card;

    @OneToOne
    @JoinColumn(name = "playerid")
    @Getter
    @Setter
    private Player player;

    @Getter
    @Setter
    private String estimate;
}
