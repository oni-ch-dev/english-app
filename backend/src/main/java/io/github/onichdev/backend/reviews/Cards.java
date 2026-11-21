package io.github.onichdev.backend.reviews;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.github.onichdev.backend.expression.Expression;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "cards")
public class Cards {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "expression_id")
    private Expression expression;

    private short state;
    private Integer step;
    private Double stability;
    private Double difficulty;
    private Instant due;
    private Instant lastReview;
    private int reps;
    private int lapses;

    @CreationTimestamp
    private Instant createdAt;
    
    @UpdateTimestamp
    private Instant updatedAt;

    protected Cards() {}

    public Cards(Expression expression, short state, Integer step, Double stability, Double difficulty, Instant due, Instant lastReview, int reps, int lapses) {
        this.expression = expression;
        this.state = state;
        this.step = step;
        this.stability = stability;
        this.difficulty = difficulty;
        this.due = due;
        this.lastReview = lastReview;
        this.reps = reps;
        this.lapses = lapses;
    }

    public Long getId() { return id; }
    public Expression getExpression() { return expression; }
    public short getState() { return state; }
    public Integer getStep() { return step; }
    public Double getStability() { return stability; }
    public Double getDifficulty() { return difficulty; }
    public Instant getDue() { return due; }
    public Instant getLastReview() { return lastReview; }
    public int getReps() { return reps; }
    public int getLapses() { return lapses; }
}
