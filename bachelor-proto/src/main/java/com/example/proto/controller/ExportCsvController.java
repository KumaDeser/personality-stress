package com.example.proto.controller;

import com.example.proto.model.SurveyResult;
import com.example.proto.repository.SurveyResultRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ExportCsvController {

    private final SurveyResultRepository repository;

    public ExportCsvController(SurveyResultRepository repository) {
        this.repository = repository;
    }

    /**
     * Basic CSV Export (Komma-getrennt) – gut für Python/pandas.
     * Pfad: /api/export/csv
     */
    @GetMapping("/api/export/csv")
    public ResponseEntity<byte[]> exportCsvBasic() {

        List<SurveyResult> all = repository.findAll();

        StringBuilder sb = new StringBuilder();

        // Header
        sb.append("id,sessionId,studyProgram,semester,")
                .append("extraversion,agreeableness,conscientiousness,neuroticism,openness,")
                .append("pssTotal,pssHelplessness,pssSelfEfficacyReversed,timestamp\n");

// Rows
        for (SurveyResult r : all) {
            sb.append(r.id).append(",")
                    .append(r.sessionId).append(",")
                    .append(r.studyProgram).append(",")
                    .append(r.semester).append(",")
                    .append(r.extraversion).append(",")
                    .append(r.agreeableness).append(",")
                    .append(r.conscientiousness).append(",")
                    .append(r.neuroticism).append(",")
                    .append(r.openness).append(",")
                    .append(r.pssTotal).append(",")
                    .append(r.pssHelplessness).append(",")
                    .append(r.pssSelfEfficacyReversed).append(",")
                    .append(r.timestamp).append("\n");
        }


        byte[] data = sb.toString().getBytes(StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=survey_export.csv");
        headers.setContentType(new MediaType("text", "csv", StandardCharsets.UTF_8));

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    /**
     * Wissenschaftlicher CSV Export (Semikolon, Metadaten, Timestamp im Dateinamen)
     * Pfad: /api/export
     */
    @GetMapping("/api/export/scientific")
    public ResponseEntity<byte[]> exportCsvScientific() throws Exception {

        List<SurveyResult> all = repository.findAll();

        StringBuilder sb = new StringBuilder();

        // Metadaten-Kopf
        LocalDateTime now = LocalDateTime.now();
        sb.append("# Export time: ").append(now).append("\n");
        sb.append("# Records: ").append(all.size()).append("\n");
        sb.append("# Study: Personality & Stress (Bachelor Thesis)\n");

        // Header
        sb.append("id;sessionId;studyProgram;semester;")
                .append("extraversion;agreeableness;conscientiousness;neuroticism;openness;")
                .append("pssTotal;pssHelplessness;pssSelfEfficacyReversed;timestamp\n");

// Rows
        for (SurveyResult r : all) {
            sb.append(r.id).append(";")
                    .append(r.sessionId).append(";")
                    .append(r.studyProgram).append(";")
                    .append(r.semester).append(";")
                    .append(r.extraversion).append(";")
                    .append(r.agreeableness).append(";")
                    .append(r.conscientiousness).append(";")
                    .append(r.neuroticism).append(";")
                    .append(r.openness).append(";")
                    .append(r.pssTotal).append(";")
                    .append(r.pssHelplessness).append(";")
                    .append(r.pssSelfEfficacyReversed).append(";")
                    .append(r.timestamp).append("\n");
        }



        // BOM + UTF-8 (für Excel)
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(0xEF);
        out.write(0xBB);
        out.write(0xBF);
        out.write(sb.toString().getBytes(StandardCharsets.UTF_8));
        byte[] data = out.toByteArray();

        // Dateiname mit Timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss");
        String tsForFilename = now.format(formatter);
        String filename = "personality-stress-export-" + tsForFilename + ".csv";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        headers.setContentType(new MediaType("text", "csv", StandardCharsets.UTF_8));

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
