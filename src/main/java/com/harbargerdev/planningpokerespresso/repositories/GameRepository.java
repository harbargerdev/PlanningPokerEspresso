package com.harbargerdev.planningpokerespresso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.harbargerdev.planningpokerespresso.models.Game;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID>{    
}
