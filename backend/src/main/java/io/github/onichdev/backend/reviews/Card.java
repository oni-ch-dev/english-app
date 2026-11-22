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
public class Card {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "expression_id")
    private Expression expression;

    private CardState state;
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

    protected Card() {}

    public Card(Expression expression) {
        this.expression = expression;
        this.state = CardState.NEW;
        this.step = 0;
        this.due = Instant.now();
    }

    public Long getId() { return id; }
    public Expression getExpression() { return expression; }
    public CardState getState() { return state; }
    public Integer getStep() { return step; }
    public Double getStability() { return stability; }
    public Double getDifficulty() { return difficulty; }
    public Instant getDue() { return due; }
    public Instant getLastReview() { return lastReview; }
    public int getReps() { return reps; }
    public int getLapses() { return lapses; }

    public void applyReview(io.github.openspacedrepetition.Card result, boolean forgot){
        if(forgot && this.state == CardState.REVIEW){
            this.lapses++;
        }
        this.state = FsrsMapper.fromFsrsState(result.getState());
        this.step = result.getStep();
        this.stability = result.getStability();
        this.difficulty = result.getDifficulty();
        this.due = result.getDue();
        this.lastReview = result.getLastReview();
        this.reps++;
    }
}
