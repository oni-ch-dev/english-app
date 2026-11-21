package io.github.onichdev.backend.expression;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateExpressionRequest(
    @NotBlank @Size(max = 500) String text,
    String definition,
    String example
) {}
