package com.harbargerdev.planningpokerespressoapi.controllers;

import com.harbargerdev.planningpokerespressoapi.dto.response.RegisterGameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.harbargerdev.planningpokerespressoapi.dto.request.RegisterGameRequest;
import com.harbargerdev.planningpokerespressoapi.services.GameService;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/register")
    public RegisterGameResponse registerNewGame(@RequestBody RegisterGameRequest request) {

        if (request.getGameDisplayName() == null || request.getGameDisplayName().isEmpty()) {
            throw new IllegalArgumentException("Game display name is required");
        }

        if (request.getOwnerDisplayName() == null || request.getOwnerDisplayName().isEmpty()) {
            throw new IllegalArgumentException("Owner display name is required");
        }

        if (request.getOwnerPlayerType() == null || request.getOwnerPlayerType().isEmpty()) {
            throw new IllegalArgumentException("Owner player type is required");
        }

        return gameService.registerNewGame(request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
