package com.spy.server.generator.core.ports;

import jakarta.validation.constraints.NotBlank;

public interface WordGenerator {
    @NotBlank
    String generateWordByPrompt(@NotBlank String title);

    @NotBlank
    String generateWordByRandom();
}
