package com.example.proto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bfi")
public class BFIController {

    private final List<String> personalityTypes;

    public BFIController() {
        personalityTypes = new ArrayList<>(2);
        personalityTypes.add("Extraversion");
        personalityTypes.add("Neurotizismus");
    }

    @GetMapping("/types")
    public List<String> getPersonalityTypes() {
        return personalityTypes;
    }
}
