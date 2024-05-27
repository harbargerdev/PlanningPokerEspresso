package com.harbargerdev.planningpokerespressoapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harbargerdev.planningpokerespressoapi.dto.request.RegisterGameRequest;
import com.harbargerdev.planningpokerespressoapi.dto.response.RegisterGameResponse;
import com.harbargerdev.planningpokerespressoapi.services.GameService;
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
public class GameControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GameService gameService;

    private static final String BASE_URL = "/api/games";

    private RegisterGameResponse registerGameResponse;
    private RegisterGameRequest registerGameRequest;

    @BeforeEach
    void setup() {
        registerGameResponse = new RegisterGameResponse();
        registerGameResponse.setGameId("1");
        registerGameResponse.setGameDisplayName("Test Game");
        registerGameResponse.setOwnerId("1");
        registerGameResponse.setOwnerDisplayName("Test Owner");

        when(gameService.registerNewGame(any(RegisterGameRequest.class))).thenReturn(registerGameResponse);
    }

    @Test
    public void registerNewGame_NullFields_ThrowsIllegalArgumentException() throws Exception {
        // Create the invalid request
        registerGameRequest = new RegisterGameRequest();

        // Convert the request to a JSON string
        String jsonRequest = objectMapper.writeValueAsString(registerGameRequest);

        // Perform the POST request
        mockMvc.perform(post(BASE_URL + "/register")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerNewGame_EmptyGameDisplayName_ThrowsIllegalArgumentException() throws Exception {
        // Create the invalid request
        registerGameRequest = new RegisterGameRequest();
        registerGameRequest.setGameDisplayName("");

        // Convert the request to a JSON string
        String jsonRequest = objectMapper.writeValueAsString(registerGameRequest);

        // Perform the POST request
        mockMvc.perform(post(BASE_URL + "/register")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerNewGame_NullOwnerDisplayName_ThrowsIllegalArgumentException() throws Exception {
        // Create the invalid request
        registerGameRequest = new RegisterGameRequest();
        registerGameRequest.setGameDisplayName("Test Game");

        // Convert the request to a JSON string
        String jsonRequest = objectMapper.writeValueAsString(registerGameRequest);

        // Perform the POST request
        mockMvc.perform(post(BASE_URL + "/register")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerNewGame_EmptyOwnerDisplayName_ThrowsIllegalArgumentException() throws Exception {
        // Create the invalid request
        registerGameRequest = new RegisterGameRequest();
        registerGameRequest.setGameDisplayName("Test Game");
        registerGameRequest.setOwnerDisplayName("");

        // Convert the request to a JSON string
        String jsonRequest = objectMapper.writeValueAsString(registerGameRequest);

        // Perform the POST request
        mockMvc.perform(post(BASE_URL + "/register")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerNewGame_NullOwnerPlayerType_ThrowsIllegalArgumentException() throws Exception {
        // Create the invalid request
        registerGameRequest = new RegisterGameRequest();
        registerGameRequest.setGameDisplayName("Test Game");
        registerGameRequest.setOwnerDisplayName("Test Owner");

        // Convert the request to a JSON string
        String jsonRequest = objectMapper.writeValueAsString(registerGameRequest);

        // Perform the POST request
        mockMvc.perform(post(BASE_URL + "/register")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerNewGame_EmptyOwnerPlayerType_ThrowsIllegalArgumentException() throws Exception {
        // Create the invalid request
        registerGameRequest = new RegisterGameRequest();
        registerGameRequest.setGameDisplayName("Test Game");
        registerGameRequest.setOwnerDisplayName("Test Owner");
        registerGameRequest.setOwnerPlayerType("");

        // Convert the request to a JSON string
        String jsonRequest = objectMapper.writeValueAsString(registerGameRequest);

        // Perform the POST request
        mockMvc.perform(post(BASE_URL + "/register")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerNewGame_ValidRequestBody_ReturnsValidResponse() throws Exception {
        // Set up the valid request
        registerGameRequest = new RegisterGameRequest();
        registerGameRequest.setGameDisplayName("Test Game");
        registerGameRequest.setOwnerDisplayName("Test Owner");
        registerGameRequest.setOwnerPlayerType("ADMIN");

        String jsonRequest = objectMapper.writeValueAsString(registerGameRequest);

        mockMvc.perform(post(BASE_URL + "/register")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gameId").value(registerGameResponse.getGameId()))
                .andExpect(jsonPath("$.gameDisplayName").value(registerGameResponse.getGameDisplayName()))
                .andExpect(jsonPath("$.ownerId").value(registerGameResponse.getOwnerId()))
                .andExpect(jsonPath("$.ownerDisplayName").value(registerGameResponse.getOwnerDisplayName()));
    }
}
