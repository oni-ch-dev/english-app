package io.github.onichdev.backend.reviews;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.onichdev.backend.reviews.dto.DueCardResponse;
import io.github.onichdev.backend.reviews.dto.ReviewRequest;
import io.github.onichdev.backend.reviews.dto.ReviewResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping ("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("/due")
    public List<DueCardResponse> getDueCards(){
        return reviewService.getDueCards();
    }

    @PostMapping
    public ReviewResponse review(@Valid @RequestBody ReviewRequest request){
        return reviewService.review(request);
    } 
}
