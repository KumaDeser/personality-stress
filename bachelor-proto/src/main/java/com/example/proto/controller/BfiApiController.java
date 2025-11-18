package com.example.proto.controller;

import com.example.proto.model.Bfi10Request;
import com.example.proto.model.BfiScores;
import com.example.proto.service.BfiScoringService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bfi10")
@Validated   // damit @Valid auf Methoden greift
public class BfiApiController {

    private final BfiScoringService scoringService;

    public BfiApiController(BfiScoringService scoringService) {
        this.scoringService = scoringService;
    }

    @PostMapping
    public ResponseEntity<BfiScores> submit(@Valid @RequestBody Bfi10Request request) {
        BfiScores scores = scoringService.score(request);
        return ResponseEntity.ok(scores);
    }
}
