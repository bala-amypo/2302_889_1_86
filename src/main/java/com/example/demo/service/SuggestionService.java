// SuggestionService.java - com.example.demo.service
package com.example.demo.service;

import com.example.demo.entity.Suggestion;
import java.util.List;

public interface SuggestionService {
    Suggestion createSuggestion(Suggestion suggestion);
    List<Suggestion> getAllSuggestions();
    Suggestion getSuggestion(Long id);
}
