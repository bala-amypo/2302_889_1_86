// package com.example.demo.service;
// import com.example.demo.entity.Suggestion;
// import java.util.List;
// public interface SuggestionService{
//     Suggestion generateSuggestion(Long farmId);
//     Suggestion getSuggestion(Long suggestionId);
//     List<Suggestion>getSuggestionByFarm(Long farmId);
// }
package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Suggestion;

public interface SuggestionService {

    Suggestion save(Suggestion suggestion);

    List<Suggestion> findAll();

    Suggestion findById(Long id);

    void delete(Long id);
}
