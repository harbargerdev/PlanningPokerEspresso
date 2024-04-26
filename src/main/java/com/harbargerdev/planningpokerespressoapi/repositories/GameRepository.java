package com.harbargerdev.planningpokerespressoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.harbargerdev.planningpokerespressoapi.models.Game;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID>{    
}
