package com.example.proto.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Bfi10Request {

    @NotBlank(message = "sessionID must not be empty")
    public String sessionID;

    @NotNull
    @Min(1)
    @Max(5)
    public Integer q1;

    @NotNull
    @Min(1)
    @Max(5)
    public Integer q2;

    @NotNull
    @Min(1)
    @Max(5)
    public Integer q3;

    @NotNull
    @Min(1)
    @Max(5)
    public Integer q4;

    @NotNull
    @Min(1)
    @Max(5)
    public Integer q5;

    @NotNull
    @Min(1)
    @Max(5)
    public Integer q6;

    @NotNull
    @Min(1)
    @Max(5)
    public Integer q7;

    @NotNull
    @Min(1)
    @Max(5)
    public Integer q8;

    @NotNull
    @Min(1)
    @Max(5)
    public Integer q9;

    @NotNull
    @Min(1)
    @Max(5)
    public Integer q10;

    // Leerer Konstruktor für JSON Deserialisierung
    public Bfi10Request() {
    }
}
