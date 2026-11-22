package io.github.onichdev.backend.reviews.dto;

import io.github.onichdev.backend.reviews.Card;

public record DueCardResponse(Long cardId, String text, String definition, String example) {
    public static DueCardResponse from(Card card){
        var e = card.getExpression();
        return new DueCardResponse(card.getId(), e.getText(), e.getDefinition(), e.getExample());
    }
}
