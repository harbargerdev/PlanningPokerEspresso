package com.harbargerdev.planningpokerespressoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.harbargerdev.planningpokerespressoapi.models.Player;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID>{    
}
