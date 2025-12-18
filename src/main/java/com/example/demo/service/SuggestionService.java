package com.example.demo.service;
import com.example.demo.entity.Suggestion;
import java.util.List;
public interface SuggestionService{
    Suggestion generateSuggetsion(Long farmId);
    Suggestion getSuggestion(Long suggestionId);
    List<Suggestion>getSuggestionByFarm(Long farmId);
}