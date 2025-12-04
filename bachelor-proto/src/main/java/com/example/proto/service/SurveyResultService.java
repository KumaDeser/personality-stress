package com.example.proto.service;

import com.example.proto.model.SurveyResult;
import com.example.proto.repository.SurveyResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyResultService {

    private final SurveyResultRepository repo;

    public SurveyResultService(SurveyResultRepository repo) {
        this.repo = repo;
    }

    public SurveyResult save(SurveyResult result) {
        return repo.save(result);
    }

    public List<SurveyResult> findAll() {
        return repo.findAll();
    }
}
