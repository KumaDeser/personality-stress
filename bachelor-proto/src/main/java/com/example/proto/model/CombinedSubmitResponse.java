package com.example.proto.model;

import java.time.LocalDateTime;

public class CombinedSubmitResponse {

    public BfiScores bfi;
    public PssScores pss;
    public LocalDateTime timestamp;

    public CombinedSubmitResponse(BfiScores bfi, PssScores pss, LocalDateTime timestamp) {
        this.bfi = bfi;
        this.pss = pss;
        this.timestamp = timestamp;
    }
}
