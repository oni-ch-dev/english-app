package io.github.onichdev.backend.reviews.dto;

import java.time.Instant;

import io.github.onichdev.backend.reviews.CardState;

public record ReviewResponse(Long cardId, CardState state, Instant nextDue) {}
