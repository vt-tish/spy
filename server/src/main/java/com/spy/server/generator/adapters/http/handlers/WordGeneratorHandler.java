package com.spy.server.generator.adapters.http.handlers;

import com.spy.server.generator.core.dto.GenerateWordDto;
import com.spy.server.generator.core.ports.WordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WordGeneratorHandler {
    private final WordGenerator wordGenerator;

    @Autowired
    public WordGeneratorHandler(WordGenerator wordGenerator) {
        this.wordGenerator = wordGenerator;
    }

    public ResponseEntity<Map<String, String>> generateWordByPrompt(GenerateWordDto dto) {
        var result = new HashMap<String, String>();
        var response = this.wordGenerator.generateWordByPrompt(dto.prompt());
        result.put("response", response);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Map<String, String>> generateWordByRandom() {
        var result = new HashMap<String, String>();
        var response = this.wordGenerator.generateWordByRandom();
        result.put("response", response);
        return ResponseEntity.ok(result);
    }
}
