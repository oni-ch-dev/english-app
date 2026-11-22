package io.github.onichdev.backend.reviews;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import io.github.onichdev.backend.reviews.dto.DueCardResponse;
import io.github.onichdev.backend.reviews.dto.ReviewRequest;
import io.github.onichdev.backend.reviews.dto.ReviewResponse;
import io.github.openspacedrepetition.CardAndReviewLog;
import io.github.openspacedrepetition.Rating;
import io.github.openspacedrepetition.Scheduler;

@Service
public class ReviewService {
    private static final int DAILY_LIMIT = 10;
    
    private final CardRepository cardRepository;
    private final ReviewLogRepository reviewLogRepository;
    private final Scheduler scheduler;

    public ReviewService(CardRepository cardRepository, ReviewLogRepository reviewLogRepository, Scheduler scheduler) {
        this.cardRepository = cardRepository;
        this.reviewLogRepository = reviewLogRepository;
        this.scheduler = scheduler;
    }

    // if readOnly = true, then dirty checking is disabled
    @Transactional(readOnly = true)
    public List<DueCardResponse> getDueCards(){
        return cardRepository
                .findDueWithExpression(Instant.now(), Limit.of(DAILY_LIMIT))
                .stream()
                .map(DueCardResponse::from)
                .toList();
    }

    // dirty checking the card entity and saving it back to the database
    @Transactional 
    public ReviewResponse review(ReviewRequest request){
        Instant now = Instant.now();

        Card card = cardRepository.findById(request.cardId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Rating rating = toRating(request.rating());
        CardAndReviewLog result = scheduler.reviewCard(FsrsMapper.toFsrs(card), rating, now);

        card.applyReview(result.card(), rating == Rating.AGAIN);
        reviewLogRepository.save(new ReviewLog(card, (short) request.rating(), now));

        return new ReviewResponse(card.getId(), card.getState(), card.getDue());
    }

    private static Rating toRating(int value){
        return switch (value) {
            case 1 -> Rating.AGAIN;
            case 2 -> Rating.HARD;
            case 3 -> Rating.GOOD;
            case 4 -> Rating.EASY;
            default -> throw new IllegalArgumentException("Invalid rating: " + value);
        };
    }
}
