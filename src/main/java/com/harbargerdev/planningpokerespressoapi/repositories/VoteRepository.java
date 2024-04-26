package com.harbargerdev.planningpokerespressoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.harbargerdev.planningpokerespressoapi.models.Vote;

import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID>{    
}
