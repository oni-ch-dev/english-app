package io.github.onichdev.backend.reviews.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReviewRequest(@NotNull Long cardId, @Min(1) @Max(4) int rating){}
