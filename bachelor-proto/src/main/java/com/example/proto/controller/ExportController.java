package com.example.proto.controller;

import com.example.proto.model.SurveyResult;
import com.example.proto.service.SurveyResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/export")
public class ExportController {

    private final SurveyResultService resultService;

    public ExportController(SurveyResultService resultService) {
        this.resultService = resultService;
    }

    // 1) JSON Export Endpoint
    @GetMapping("/json")
    public List<SurveyResult> exportJson() {
        return resultService.findAll();
    }
}
