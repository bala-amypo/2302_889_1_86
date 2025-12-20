// SuggestionController.java - com.example.demo.controller
package com.example.demo.controller;

import com.example.demo.entity.Suggestion;
import com.example.demo.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/suggestions")
@Tag(name = "Suggestions")
public class SuggestionController {
    
    private final SuggestionService suggestionService;

    @Autowired
    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @PostMapping("/{farmId}")
    public ResponseEntity<Suggestion> generateSuggestion(@PathVariable Long farmId, Authentication auth) {
        Suggestion suggestion = suggestionService.generateSuggestion(farmId);
        return ResponseEntity.ok(suggestion);
    }

    @GetMapping("/{suggestionId}")
    public ResponseEntity<Suggestion> getSuggestion(@PathVariable Long suggestionId) {
        Suggestion suggestion = suggestionService.getSuggestion(suggestionId);
        return ResponseEntity.ok(suggestion);
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<Suggestion>> getSuggestionsByFarm(@PathVariable Long farmId) {
        List<Suggestion> suggestions = suggestionService.getSuggestionsByFarm(farmId);
        return ResponseEntity.ok(suggestions);
    }
}
