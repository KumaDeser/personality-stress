package com.example.proto.service;

import com.example.proto.model.Pss10Request;
import com.example.proto.model.PssScores;
import org.springframework.stereotype.Service;

@Service
public class PssScoringService {

    // Reverse-Scoring für PSS-Items (0-4 Skala)
    private int rev(Integer v) {
        if (v == null) return 0;  // sollte dank @NotNull nicht vorkommen, aber zur Sicherheit
        return 4 - v;
    }

    public PssScores score(Pss10Request r) {

        // Hilflosigkeit: Items 1, 2, 3, 6, 9, 10
        int helplessness =
                r.q1
                        + r.q2
                        + r.q3
                        + r.q6
                        + r.q9
                        + r.q10;

        // Selbstwirksamkeit (invertiert): Items 4, 5, 7, 8 -> erst rev(), dann summieren
        int selfEfficacyReversed =
                rev(r.q4)
                        + rev(r.q5)
                        + rev(r.q7)
                        + rev(r.q8);

        // Gesamt-PSS-Score
        int total = helplessness + selfEfficacyReversed;

        return new PssScores(total, helplessness, selfEfficacyReversed);
    }
}
