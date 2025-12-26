package com.example.demo.controller;

import com.example.demo.entity.Suggestion;
import com.example.demo.service.SuggestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {

    private final SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @PostMapping("/generate/{farmId}")
    public ResponseEntity<Suggestion> generateSuggestion(
            @PathVariable Long farmId, 
            Authentication auth) {
        
        Long userId = (Long) auth.getPrincipal();
        // Verify user owns the farm (add farm service check if needed)
        
        Suggestion suggestion = suggestionService.generateSuggestion(farmId);
        return ResponseEntity.ok(suggestion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Suggestion> getSuggestion(@PathVariable Long id) {
        Suggestion suggestion = suggestionService.getSuggestion(id);
        return suggestion != null ? 
            ResponseEntity.ok(suggestion) : 
            ResponseEntity.notFound().build();
    }
}
