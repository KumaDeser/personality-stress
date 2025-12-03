package com.example.proto.controller;

import com.example.proto.model.Pss10Request;
import com.example.proto.model.PssScores;
import com.example.proto.service.PssScoringService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pss10")
public class PssApiController {

    private final PssScoringService scoringService;

    public PssApiController(PssScoringService scoringService) {
        this.scoringService = scoringService;
    }

    @PostMapping
    public ResponseEntity<PssScores> submit(@Valid @RequestBody Pss10Request request) {
        PssScores scores = scoringService.score(request);
        return ResponseEntity.ok(scores);
    }
}
