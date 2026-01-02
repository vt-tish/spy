package com.spy.server.generator.adapters.http.controllers;

import com.spy.server.generator.adapters.http.handlers.WordGeneratorHandler;
import com.spy.server.generator.core.dto.GenerateWordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generator")
public class WordGeneratorController {
    private final WordGeneratorHandler wordGeneratorHandler;

    @Autowired
    public WordGeneratorController(WordGeneratorHandler wordGeneratorHandler) {
        this.wordGeneratorHandler = wordGeneratorHandler;
    }

    @GetMapping("/generate-word-by-prompt")
    public ResponseEntity<?> generateWordByPrompt(@RequestBody GenerateWordDto dto) {
        return wordGeneratorHandler.generateWordByPrompt(dto);
    }

    @GetMapping("/generate-word-by-random")
    public ResponseEntity<?> generateWordByRandom() {
        return wordGeneratorHandler.generateWordByRandom();
    }
}
