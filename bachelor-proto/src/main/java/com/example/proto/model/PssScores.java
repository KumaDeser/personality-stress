package com.example.proto.model;

public class PssScores {

    // Gesamt-PSS-Score (je höher, desto mehr wahrgenommener Stress)
    public Integer total;

    // Subskala: Hilflosigkeit (Items 1,2,3,6,9,10)
    public Integer helplessness;

    // Subskala: Selbstwirksamkeit (invertiert) (Items 4,5,7,8, erst umgepolt, dann summiert)
    public Integer selfEfficacyReversed;

    public PssScores(Integer total, Integer helplessness, Integer selfEfficacyReversed) {
        this.total = total;
        this.helplessness = helplessness;
        this.selfEfficacyReversed = selfEfficacyReversed;
    }
}
