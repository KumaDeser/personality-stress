package com.example.proto.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class CombinedSubmitRequest {

    @NotBlank
    public String sessionID;

    @NotNull @Valid
    public Bfi10Request bfi;

    @NotNull @Valid
    public Pss10Request pss;

    //  Demografie
    @NotBlank(message = "Studiengang erforderlich")
    public String studyProgram; // AIN / GIB / WIN

    @Min(1) @Max(7)
    @NotNull
    public Integer semester;

    public CombinedSubmitRequest() {}
}
