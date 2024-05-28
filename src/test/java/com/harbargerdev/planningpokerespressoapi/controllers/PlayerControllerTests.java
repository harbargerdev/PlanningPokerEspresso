package com.harbargerdev.planningpokerespressoapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harbargerdev.planningpokerespressoapi.dto.request.RegisterPlayerRequest;
import com.harbargerdev.planningpokerespressoapi.dto.response.RegisterGameResponse;
import com.harbargerdev.planningpokerespressoapi.dto.response.RegisterPlayerResponse;
import com.harbargerdev.planningpokerespressoapi.services.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerService playerService;

    private static final String BASE_URL = "/api/players";

    @Test
    public void registerNewPlayer_NullFields_ThrowsIllegalArgumentException() throws Exception {

        // Arrange
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest();

        // Create Json Body
        String jsonRequest = objectMapper.writeValueAsString(registerPlayerRequest);

        // Act and Assert
        mockMvc.perform(post(BASE_URL + "/register")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerNewPlayer_EmptyGameId_ThrowsIllegalArgumentException() throws Exception {

        // Arrange
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest();
        registerPlayerRequest.setGameId("");

        // Create Json Body
        String jsonRequest = objectMapper.writeValueAsString(registerPlayerRequest);

        // Act and Assert
        mockMvc.perform(post(BASE_URL + "/register")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerNewPlayer_NullPlayerDisplayName_ThrowsIllegalArgumentException() throws Exception {

        // Arrange
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest();
        registerPlayerRequest.setPlayerDisplayName(null);

        // Create Json Body
        String jsonRequest = objectMapper.writeValueAsString(registerPlayerRequest);

        // Act and Assert
        mockMvc.perform(post(BASE_URL + "/register")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerNewPlayer_EmptyPlayerDisplayName_ThrowsIllegalArgumentException() throws Exception {

        // Arrange
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest();
        registerPlayerRequest.setPlayerDisplayName("");

        // Create Json Body
        String jsonRequest = objectMapper.writeValueAsString(registerPlayerRequest);

        // Act and Assert
        mockMvc.perform(post(BASE_URL + "/register")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerNewPlayer_NullPlayerType_ThrowsIllegalArgumentException() throws Exception {

        // Arrange
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest();
        registerPlayerRequest.setPlayerType(null);

        // Create Json Body
        String jsonRequest = objectMapper.writeValueAsString(registerPlayerRequest);

        // Act and Assert
        mockMvc.perform(post(BASE_URL + "/register")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerNewPlayer_EmptyPlayerType_ThrowsIllegalArgumentException() throws Exception {

        // Arrange
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest();
        registerPlayerRequest.setPlayerType("");

        // Create Json Body
        String jsonRequest = objectMapper.writeValueAsString(registerPlayerRequest);

        // Act and Assert
        mockMvc.perform(post(BASE_URL + "/register")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerNewPlayer_ValidRequest_ReturnsOk() throws Exception {

        // Arrange
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest();
        registerPlayerRequest.setGameId("some=game-id");
        registerPlayerRequest.setPlayerDisplayName("Test Player");
        registerPlayerRequest.setPlayerType("AUDIENCE");

        // Create Json Body
        String jsonRequest = objectMapper.writeValueAsString(registerPlayerRequest);

        RegisterPlayerResponse registerPlayerResponse = new RegisterPlayerResponse();
        registerPlayerResponse = new RegisterPlayerResponse();
        registerPlayerResponse.setGameId("some-game-id");
        registerPlayerResponse.setPlayerId("some-player-id");
        registerPlayerResponse.setPlayerDisplayName("Player Name");
        registerPlayerResponse.setPlayerType("AUDIENCE");

        when(playerService.registerNewPlayer(any(RegisterPlayerRequest.class))).thenReturn(registerPlayerResponse);

        // Act and Assert
        mockMvc.perform(post(BASE_URL + "/register")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerId").exists())
                .andExpect(jsonPath("$.playerId").isNotEmpty());
    }
}
