package com.harbargerdev.planningpokerespressoapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harbargerdev.planningpokerespressoapi.dto.request.NewGameRequest;
import com.harbargerdev.planningpokerespressoapi.dto.response.NewGameResponse;
import com.harbargerdev.planningpokerespressoapi.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GameService gameService;

    @BeforeEach
    void setup() {
        NewGameResponse response = new NewGameResponse();
        response.setGameId(UUID.randomUUID());
        response.setDisplayName("Test Game");
        response.setStartTime(LocalDateTime.now());

        //when(gameService.createNewGame(any(NewGameRequest.class))).thenReturn(response);
    }
}
