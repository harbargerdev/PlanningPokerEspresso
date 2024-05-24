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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String voteId;

    @ManyToOne
    @JoinColumn(name = "cardid")
    private Card card;

    @OneToOne
    @JoinColumn(name = "playerid")
    private Player player;

    private String estimate;
}
