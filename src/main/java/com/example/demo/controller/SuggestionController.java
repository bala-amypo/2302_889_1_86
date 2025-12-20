// package com.example.demo.controller;

// import com.example.demo.entity.Suggestion;
// import com.example.demo.service.SuggestionService;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import java.util.List;

// @RestController
// @RequestMapping("/suggestions")
// public class SuggestionController {
//     @Autowired
//     SuggestionService suggestionService;

//     @PostMapping("/{farmId}")
//     public Suggestion generateSuggestion(@PathVariable Long farmId) {
//         return suggestionService.generateSuggestion(farmId);
//     }
//     @GetMapping("/{suggestionId}")
//     public Suggestion getSuggestion(@PathVariable Long suggestionId) {
//         return suggestionService.getSuggestion(suggestionId);
//     }

//     @GetMapping("/farm/{farmId}")
//     public List<Suggestion> getSuggestionByFarm(@PathVariable Long farmId) {
//         return suggestionService.getSuggestionByFarm(farmId);
//     }
// }
package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Suggestion;
import com.example.demo.service.SuggestionService;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {

    private final SuggestionService service;

    public SuggestionController(SuggestionService service) {
        this.service = service;
    }

    @PostMapping
    public Suggestion create(@RequestBody Suggestion suggestion) {
        return service.save(suggestion);
    }

    @GetMapping
    public List<Suggestion> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Suggestion getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Suggestion deleted";
    }
}
