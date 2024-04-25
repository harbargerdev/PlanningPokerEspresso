package com.harbargerdev.planningpokerespresso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.harbargerdev.planningpokerespresso.models.Card;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID>{    
}
