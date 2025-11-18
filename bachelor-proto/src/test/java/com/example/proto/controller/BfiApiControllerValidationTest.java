package com.example.proto.controller;

import com.example.proto.model.Bfi10Request;
import com.example.proto.model.BfiScores;
import com.example.proto.service.BfiScoringService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BfiApiController.class)
@AutoConfigureMockMvc
class BfiApiControllerValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BfiScoringService scoringService;

    @Test
    void whenMissingRequiredField_thenReturns400() throws Exception {
        Bfi10Request request = new Bfi10Request();
        request.sessionID = "";
        request.q1 = 3; request.q2 = 4; request.q3 = 3; request.q4 = 2; request.q5 = 4;
        request.q6 = 5; request.q7 = 2; request.q8 = 3; request.q9 = 4; request.q10 = 1;

        mockMvc.perform(post("/api/bfi10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        Bfi10Request request = new Bfi10Request();
        request.sessionID = "test123";
        request.q1 = 3; request.q2 = 4; request.q3 = 3; request.q4 = 2; request.q5 = 4;
        request.q6 = 5; request.q7 = 2; request.q8 = 3; request.q9 = 4; request.q10 = 1;

        when(scoringService.score(any(Bfi10Request.class)))
                .thenReturn(new BfiScores(3.0, 3.0, 3.0, 3.0, 3.0));

        mockMvc.perform(post("/api/bfi10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}