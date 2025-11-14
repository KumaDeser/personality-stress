package com.example.proto.service;

import com.example.proto.model.Bfi10Request;
import com.example.proto.model.BfiScores;
import org.springframework.stereotype.Service;

@Service
public class BfiScoringService {

    private int rev(Integer v) {
        if (v == null) return 0; // später bessere Fehlerbehandlung möglich
        return 6 - v;  // BFI-10; 1<->5, 2<->4, 3 bleibt 3
    }

    public BfiScores score(Bfi10Request r) {
        // Extraversion: q1 (+), q6 (reverse)
        Double extra = (r.q1 + rev(r.q6)) / 2.0;

        // Verträglichkeit: q2 (reverse), q7 (+)
        Double agree = (rev(r.q2) + r.q7) / 2.0;

        // Gewissenhaftigkeit: q3 (reverse), q8 (+)
        Double consc = (rev(r.q3) + r.q8) / 2.0;

        // Neurotizismus: q4 (reverse), q9 (+)
        Double neuro = (rev(r.q4) + r.q9) /2.0;

        // Offenheit: q5 (+), q10 (reverse)
        Double open = (r.q5 + rev(r.q10)) / 2.0;

        return new BfiScores(extra, agree, consc, neuro, open);
    }
}
