package com.example.proto.controller;


import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class StressLevelController {

    // Liste mit Stressleveln (integer/strings) Beispielhafte Speicherung der Stress-Level-Antworten

    private List<Integer> stressLevels = new ArrayList<>();
    // POST: neuen Stresslevel hinzufügen
    @PostMapping
    public String addStressLevel(@RequestBody int level) {
        stressLevels.add(level);
        return "Stress level" + level + "wurde hinzugefügt.";
    }
    // GET: alle gespeicherten Werte abrufen
    @GetMapping
    public List<Integer> getAllStressLevels() {
        return stressLevels;
    }

    // Beispielhafte Berechnung des Durchschnitts (wird später erweitert)
    @GetMapping("/average")
    public double calculateAverageStress() {
        if (stressLevels.isEmpty()) return 0.0;
        return stressLevels.stream().mapToInt(Integer::intValue).average().orElse(0.0);

    }

}
