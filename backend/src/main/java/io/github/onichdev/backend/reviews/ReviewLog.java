package io.github.onichdev.backend.reviews;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "review_logs")
public class ReviewLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One card can have many review logs.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "card_id")
    private Card card;

    private short rating;
    private Instant reviewedAt;

    protected ReviewLog() {}
    public ReviewLog(Card card, short rating, Instant reviewedAt) {
        this.card = card;
        this.rating = rating;
        this.reviewedAt = reviewedAt;
    }

    public Long getId() { return id; }
    public Card getCard() { return card; }
    public short getRating() { return rating; }
    public Instant getReviewedAt() { return reviewedAt; }
}
