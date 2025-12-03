package com.example.proto.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CombinedSubmitRequest {

    @NotBlank
    public String sessionID;

    @NotNull
    @Valid
    public Bfi10Request bfi;

    @NotNull
    @Valid
    public Pss10Request pss;

    public CombinedSubmitRequest() {
    }
}
