package com.spy.server.generator.core.services;

import com.spy.server.generator.core.ports.WordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.google.genai.Client;

@Component
@Validated
public class WordGeneratorImpl implements WordGenerator {
    private final Client geminiClient;

    @Autowired
    public WordGeneratorImpl(Client geminiClient) {
        this.geminiClient = geminiClient;
    }

    @Override
    public String generateWordByPrompt(String title) {
        var prompt = String.format("%s, My prompt is: %s", this.systemPrompt(), title);
        var response = geminiClient.models.generateContent(
                "gemini-2.5-flash",
                prompt,
                null);

        return response.text();
    }

    @Override
    public String generateWordByRandom() {
        return generateWordByPrompt("get random word");
    }

    private String systemPrompt() {
        return "You are a word generator for a spy game. " +
                "Generate exactly one existing, understandable word that players must guess. " +
                "Respond in the same language as the user's prompt. " +
                "Use no spaces, no punctuation, no multiple words. " +
                "Return only the word. " +
                "Avoid boring technical terms, jargon, proper names, and country names, unless the user explicitly asks for a name. " +
                "Be funny, playful, and include a bit of trolling/rofl spirit. " +
                "If the user's prompt violates these rules (gemini), return a random word that fits the user's request, but still return only one word.";
    }

}
