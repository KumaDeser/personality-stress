package com.example.proto.controller;

import com.example.proto.model.*;
import com.example.proto.service.BfiScoringService;
import com.example.proto.service.PssScoringService;
import com.example.proto.service.SurveyResultService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/submit-all")
@Validated
public class CombinedSubmitController {

    private final BfiScoringService bfiService;
    private final PssScoringService pssService;
    private final SurveyResultService resultService;

    public CombinedSubmitController(
            BfiScoringService bfiService,
            PssScoringService pssService,
            SurveyResultService resultService
    ) {
        this.bfiService = bfiService;
        this.pssService = pssService;
        this.resultService = resultService;
    }

    @PostMapping
    public ResponseEntity<CombinedSubmitResponse> submit(@Valid @RequestBody CombinedSubmitRequest request) {

        BfiScores bfi = bfiService.score(request.bfi);
        PssScores pss = pssService.score(request.pss);

        LocalDateTime now = LocalDateTime.now();

        SurveyResult entity = new SurveyResult(
                request.sessionID,
                bfi.extraversion,
                bfi.agreeableness,
                bfi.conscientiousness,
                bfi.neuroticism,
                bfi.openness,
                pss.total,
                pss.helplessness,
                pss.selfEfficacyReversed,
                now
        );

        resultService.save(entity);

        CombinedSubmitResponse response = new CombinedSubmitResponse(bfi, pss, now);
        return ResponseEntity.ok(response);
    }
}
