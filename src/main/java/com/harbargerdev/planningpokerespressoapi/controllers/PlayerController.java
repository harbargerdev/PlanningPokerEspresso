package com.harbargerdev.planningpokerespressoapi.controllers;

import com.harbargerdev.planningpokerespressoapi.dto.request.RegisterPlayerRequest;
import com.harbargerdev.planningpokerespressoapi.dto.response.RegisterPlayerResponse;
import com.harbargerdev.planningpokerespressoapi.services.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;

    @PostMapping("/register")
    public RegisterPlayerResponse registerNewPlayer(@RequestBody RegisterPlayerRequest request) throws Exception {

        if (request.getGameId() == null || request.getGameId().isEmpty()) {
            throw new IllegalArgumentException("Game ID is required");
        }

        if (request.getPlayerDisplayName() == null || request.getPlayerDisplayName().isEmpty()) {
            throw new IllegalArgumentException("Player display name is required");
        }

        if (request.getPlayerType() == null || request.getPlayerType().isEmpty()) {
            throw new IllegalArgumentException("Player type is required");
        }

        return playerService.registerNewPlayer(request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing request");
    }
}
