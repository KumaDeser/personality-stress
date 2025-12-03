
package com.example.proto.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Pss10Request {

    @NotBlank(message = "sessionID must not be empty")
    public String sessionID;

    // PSS-10 verwendet 0-4 Skala: 0=nie, 1=selten, 2=manchmal, 3=oft, 4=sehr oft

    @NotNull
    @Min(0)
    @Max(4)
    public Integer q1;  // "Aufgeregt wegen unerwarteter Ereignisse"

    @NotNull
    @Min(0)
    @Max(4)
    public Integer q2;  // "Unfähig wichtige Dinge zu kontrollieren"

    @NotNull
    @Min(0)
    @Max(4)
    public Integer q3;  // "Nervös und gestresst gefühlt"

    @NotNull
    @Min(0)
    @Max(4)
    public Integer q4;  // "Zuversichtlich persönliche Probleme handhaben" (REVERSE)

    @NotNull
    @Min(0)
    @Max(4)
    public Integer q5;  // "Gefühl dass Dinge gut laufen" (REVERSE)

    @NotNull
    @Min(0)
    @Max(4)
    public Integer q6;  // "Konnte nicht mit allem fertig werden"

    @NotNull
    @Min(0)
    @Max(4)
    public Integer q7;  // "Fähig Ärger zu kontrollieren" (REVERSE)

    @NotNull
    @Min(0)
    @Max(4)
    public Integer q8;  // "Gefühl alles im Griff zu haben" (REVERSE)

    @NotNull
    @Min(0)
    @Max(4)
    public Integer q9;  // "Verärgert über Dinge außerhalb der Kontrolle"

    @NotNull
    @Min(0)
    @Max(4)
    public Integer q10; // "Schwierigkeiten türmen sich auf"

    // Leerer Konstruktor für JSON Deserialisierung
    public Pss10Request() {
    }
}