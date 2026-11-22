package io.github.onichdev.backend.expression;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.onichdev.backend.expression.dto.CreateExpressionRequest;
import io.github.onichdev.backend.expression.dto.ExpressionResponse;
import io.github.onichdev.backend.reviews.Card;
import io.github.onichdev.backend.reviews.CardRepository;

@Service
public class ExpressionService {
    private final ExpressionRepository expressionRepository;
    private final CardRepository cardRepository;

    public ExpressionService(ExpressionRepository expressionRepository, CardRepository cardRepository) {
        this.expressionRepository = expressionRepository;
        this.cardRepository = cardRepository;
    }

    @Transactional
    public ExpressionResponse create(CreateExpressionRequest request) {
        Expression expression = expressionRepository.save(
            new Expression(request.text(), request.definition(), request.example())
        );
        cardRepository.save(new Card(expression));
        return ExpressionResponse.from(expression);
    }
}
