// SuggestionController.java - com.example.demo.controller
package com.example.demo.controller;

import com.example.demo.entity.Suggestion;
import com.example.demo.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {
    
    private final SuggestionService suggestionService;

    @Autowired
    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @PostMapping
    public Suggestion createSuggestion(@RequestBody Suggestion suggestion) {
        return suggestionService.saveSuggestion(suggestion);
    }

    @GetMapping
    public List<Suggestion> getAllSuggestions() {
        return suggestionService.getAllSuggestions();
    }

    @GetMapping("/{id}")
    public Suggestion getSuggestion(@PathVariable Long id) {
        return suggestionService.getSuggestion(id);
    }

    @PutMapping("/{id}")
    public Suggestion updateSuggestion(@PathVariable Long id, @RequestBody Suggestion suggestion) {
        return suggestionService.updateSuggestion(id, suggestion);
    }

    @DeleteMapping("/{id}")
    public void deleteSuggestion(@PathVariable Long id) {
        suggestionService.deleteSuggestion(id);
    }
}
