package com.example.proto.service;

import com.example.proto.model.Bfi10Request;
import com.example.proto.model.BfiScores;
import org.springframework.stereotype.Service;

@Service
public class BfiScoringService {

    /** Reverse-Scoring für 1–5 Skala nach Rammstedt (2007):
     * 1→5, 2→4, 3→3, 4→2, 5→1
     */
    private int rev(int v) { return 6 - v; }

    public BfiScores score(Bfi10Request r) {

        // 1) Extraversion → Item 1 (R) + Item 6
        double extraversion = (rev(r.q1) + r.q6) / 2.0;

        // 2) Agreeableness/Verträglichkeit → Item 2 + Item 7 (R)
        double agreeableness = (r.q2 + rev(r.q7)) / 2.0;

        // 3) Conscientiousness/Gewissenhaftigkeit → Item 3 (R) + Item 8
        double conscientiousness = (rev(r.q3) + r.q8) / 2.0;

        // 4) Neuroticism/Neurotizismus → Item 4 (R) + Item 9
        double neuroticism = (rev(r.q4) + r.q9) / 2.0;

        // 5) Openness/Offenheit → Item 5 (R) + Item 10
        double openness = (rev(r.q5) + r.q10) / 2.0;

        return new BfiScores(
                extraversion,
                agreeableness,
                conscientiousness,
                neuroticism,
                openness
        );
    }
}
