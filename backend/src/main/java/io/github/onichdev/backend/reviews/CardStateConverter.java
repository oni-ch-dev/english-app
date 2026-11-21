package io.github.onichdev.backend.reviews;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CardStateConverter implements AttributeConverter<CardState, Short> {

    @Override
    public Short convertToDatabaseColumn(CardState state) {
        return state == null ? null : state.getCode();
    }

    @Override
    public CardState convertToEntityAttribute(Short code) {
        return code == null ? null : CardState.fromCode(code);
    }
}
