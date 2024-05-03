package com.harbargerdev.planningpokerespressoapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harbargerdev.planningpokerespressoapi.dto.request.NewGameRequest;
import com.harbargerdev.planningpokerespressoapi.dto.response.NewGameResponse;
import com.harbargerdev.planningpokerespressoapi.services.GameService;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping("/create")
    public NewGameResponse createNewGame(@RequestBody NewGameRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (request.getDisplayName() == null || request.getDisplayName().isEmpty()) {
            throw new IllegalArgumentException("Display name cannot be null or empty");
        }

        if (request.getGameOwnerId() == null) {
            throw new IllegalArgumentException("Game owner ID cannot be null or emtpy");
        }

        return gameService.createNewGame(request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
