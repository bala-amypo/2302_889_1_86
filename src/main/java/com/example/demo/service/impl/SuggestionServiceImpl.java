// SuggestionServiceImpl.java - com.example.demo.service.impl
package com.example.demo.service.impl;

import com.example.demo.entity.Suggestion;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SuggestionServiceImpl implements SuggestionService {
    
    private final SuggestionRepository suggestionRepository;

    @Autowired
    public SuggestionServiceImpl(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

    @Override
    public Suggestion createSuggestion(Suggestion suggestion) {
        return suggestionRepository.save(suggestion);
    }

    @Override
    public List<Suggestion> getAllSuggestions() {
        return suggestionRepository.findAll();
    }

    @Override
    public Suggestion getSuggestion(Long id) {
        return suggestionRepository.findById(id).orElse(null);
    }
}
