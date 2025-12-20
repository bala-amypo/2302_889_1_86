
package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Suggestion;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.SuggestionService;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    private final SuggestionRepository repository;

    public SuggestionServiceImpl(SuggestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Suggestion save(Suggestion suggestion) {
        return repository.save(suggestion);
    }

    @Override
    public List<Suggestion> findAll() {
        return repository.findAll();
    }

    @Override
    public Suggestion findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}


// package com.example.demo.service.impl;

// import com.example.demo.entity.Farm;
// import com.example.demo.entity.Suggestion;
// import com.example.demo.repository.FarmRepository;
// import com.example.demo.repository.SuggestionRepository;
// import com.example.demo.service.SuggestionService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;
// import org.springframework.web.server.ResponseStatusException;
// import java.util.List;
// import static org.springframework.http.HttpStatus.NOT_FOUND;

// @Service
// @RequiredArgsConstructor
// public class SuggestionServiceImpl implements SuggestionService {

//     private final FarmRepository farmRepository;
//     private final SuggestionRepository suggestionRepository;

//     @Override
//     public Suggestion generateSuggestion(Long farmId) {
//         Farm farm = farmRepository.findById(farmId)
//                 .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));

//         Suggestion suggestion = Suggestion.builder()
//                 .farm(farm)
//                 .suggestedCrops("Wheat,Rice")
//                 .suggestedFertilizers("Urea,10-10-10")
//                 .build();

//         return suggestionRepository.save(suggestion);
//     }

//     @Override
//     public Suggestion getSuggestion(Long suggestionId) {
//         return suggestionRepository.findById(suggestionId)
//                 .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
//     }

//     @Override
//     public List<Suggestion> getSuggestionByFarm(Long farmId) {
//         return suggestionRepository.findById(farmId);
//     }
// }

