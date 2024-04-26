package com.harbargerdev.planningpokerespressoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.harbargerdev.planningpokerespressoapi.models.Card;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID>{    
}
