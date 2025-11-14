package com.example.proto.controller;

import com.example.proto.model.Bfi10Request;
import com.example.proto.model.BfiScores;
import com.example.proto.service.BfiScoringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bfi10")
public class BfiApiController {

    private final BfiScoringService scoringService;

    public BfiApiController(BfiScoringService scoringService) {
        this.scoringService = scoringService;
    }

    @PostMapping

    public ResponseEntity<BfiScores> submit(@RequestBody Bfi10Request request) {
        BfiScores scores = scoringService.score(request);
        return ResponseEntity.ok(scores);
    }
}
