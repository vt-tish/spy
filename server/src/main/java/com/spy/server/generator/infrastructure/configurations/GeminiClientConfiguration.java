package com.spy.server.generator.infrastructure.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.genai.Client;

@Configuration
public class GeminiClientConfiguration {
    @Value("${gemini.api-key}")
    private String geminiApiKey;

    @Bean
    public Client getGeminiClient() {
        return Client.builder().apiKey(geminiApiKey).build();
    }
}
