package io.github.onichdev.backend.reviews;

public enum CardState {
    NEW((short) 0),
    LEARNING((short) 1),
    REVIEW((short) 2),
    RELEARNING((short) 3);

    private final short code;

    CardState(short code) {
        this.code = code;
    }

    public short getCode() {
        return code;
    }

    public static CardState fromCode(short code) {
        for (CardState state : CardState.values()) {
            if (state.getCode() == code) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid CardState code: " + code);
    }
}
    