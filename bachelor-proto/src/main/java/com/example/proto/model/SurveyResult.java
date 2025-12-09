package com.example.proto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class SurveyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String sessionId;

    // Big Five Scores
    public Double extraversion;
    public Double agreeableness;
    public Double conscientiousness;
    public Double neuroticism;
    public Double openness;

    // PSS Scores
    public Integer pssTotal;
    public Integer pssHelplessness;
    public Integer pssSelfEfficacyReversed;

    // Studienprogramm (AIN / GIB / WIN)
    public String studyProgram;
    public int semester;

    // Zeit der Einreichung
    public LocalDateTime timestamp;

    public SurveyResult() {
    }

    public SurveyResult(
            String sessionId,
            Double extraversion,
            Double agreeableness,
            Double conscientiousness,
            Double neuroticism,
            Double openness,
            Integer pssTotal,
            Integer pssHelplessness,
            Integer pssSelfEfficacyReversed,
            String studyProgram,  //AIN / GIB / WIN
            int semester,        // 1-7
            LocalDateTime timestamp
    ) {
        this.sessionId = sessionId;
        this.extraversion = extraversion;
        this.agreeableness = agreeableness;
        this.conscientiousness = conscientiousness;
        this.neuroticism = neuroticism;
        this.openness = openness;
        this.pssTotal = pssTotal;
        this.pssHelplessness = pssHelplessness;
        this.pssSelfEfficacyReversed = pssSelfEfficacyReversed;
        this.studyProgram = studyProgram;
        this.semester = semester;
        this.timestamp = timestamp;
    }
}
