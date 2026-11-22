package io.github.onichdev.backend.expression.dto;

import io.github.onichdev.backend.expression.Expression;

public record ExpressionResponse(
    Long id,
    String text,
    String definition,
    String example
){
    public static ExpressionResponse from(Expression e) {
        return new ExpressionResponse(
            e.getId(), e.getText(), e.getDefinition(), e.getExample()
        );
    }
}