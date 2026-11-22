package io.github.onichdev.backend.reviews;

import io.github.openspacedrepetition.State;

final class FsrsMapper {
    private FsrsMapper() {}

    static io.github.openspacedrepetition.Card toFsrs(Card card) {
        return io.github.openspacedrepetition.Card.builder()
                .cardId(card.getId().intValue())
                .state(toFsrsState(card.getState()))
                .step(card.getStep())
                .stability(card.getStability())
                .difficulty(card.getDifficulty())
                .due(card.getDue())
                .lastReview(card.getLastReview())
                .build();
    }

    static State toFsrsState(CardState state){
        return switch(state){
            case NEW, LEARNING -> State.LEARNING;
            case REVIEW -> State.REVIEW;
            case RELEARNING -> State.RELEARNING;  
        };
    }

    static CardState fromFsrsState(State state){
        return switch(state){
            case LEARNING -> CardState.LEARNING;
            case REVIEW -> CardState.REVIEW;
            case RELEARNING -> CardState.RELEARNING;
        };
    }
}
