package com.example.proto.controller;

import com.example.proto.model.*;
import com.example.proto.service.BfiScoringService;
import com.example.proto.service.PssScoringService;
import com.example.proto.service.SurveyResultService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CombinedSubmitResponse> submit(@Valid @RequestBody CombinedSubmitRequest req) {

        // Sicherheits-Check — verhindert gemischte Personen-Daten
        if (!req.sessionID.equals(req.bfi.sessionID) ||
                !req.sessionID.equals(req.pss.sessionID)) {
            return ResponseEntity.badRequest().build();
        }

        // Score berechnen
        BfiScores bfi = bfiService.score(req.bfi);
        PssScores pss = pssService.score(req.pss);

        LocalDateTime now = LocalDateTime.now();

        // Speicherung — jetzt MIT studyProgram & semester
        SurveyResult entity = new SurveyResult(
                req.sessionID,
                bfi.extraversion,
                bfi.agreeableness,
                bfi.conscientiousness,
                bfi.neuroticism,
                bfi.openness,
                pss.total,
                pss.helplessness,
                pss.selfEfficacyReversed,
                req.studyProgram,   // Neu!
                req.semester,       // Neu!
                now
        );

        resultService.save(entity);

        return ResponseEntity.ok(new CombinedSubmitResponse(bfi, pss, now));
    }
}
