package com.example.proto.service;

import com.example.proto.model.Bfi10Request;
import com.example.proto.model.BfiScores;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BfiScoringServiceTest {

    private final BfiScoringService service = new BfiScoringService();

    @Test
    public void testScoreCalculation() {
        // Arrange - Eingabedaten vorbereiten
        Bfi10Request input = new Bfi10Request();
        input.q1 = 4; // +
        input.q2 = 2; // reverse
        input.q3 = 1; // reverse
        input.q4 = 2; // reverse
        input.q5 = 5; // +
        input.q6 = 2; // reverse
        input.q7 = 4; // +
        input.q8 = 5; // +
        input.q9 = 4; // +
        input.q10 = 1; // reverse

        // Act
        BfiScores result = service.score(input);

        // Assert
        assertEquals(4.0, result.extraversion, 0.01);
        assertEquals(4.0, result.agreeableness, 0.01);
        assertEquals(5.0, result.conscientiousness, 0.01);
        assertEquals(4.0, result.neuroticism, 0.01);
        assertEquals(5.0, result.openness, 0.01);
    }
}
