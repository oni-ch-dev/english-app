package io.github.onichdev.backend.expression;

import org.springframework.data.jpa.repository.JpaRepository;

// window for querying the database
public interface ExpressionRepository extends JpaRepository<Expression, Long> {
    
}
