CREATE TABLE expressions (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    text        VARCHAR(500) NOT NULL,
    definition  TEXT,
    example     TEXT,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE cards (
    id              BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    expression_id   BIGINT NOT NULL UNIQUE REFERENCES expressions(id) ON DELETE CASCADE,
    state           SMALLINT NOT NULL DEFAULT 0,
    step            INTEGER,
    stability       DOUBLE PRECISION,
    difficulty      DOUBLE PRECISION,
    due             TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_review     TIMESTAMPTZ,
    reps            INTEGER NOT NULL DEFAULT 0,
    lapses          INTEGER NOT NULL DEFAULT 0,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_cards_due ON cards(due);

CREATE TABLE review_logs (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    card_id     BIGINT NOT NULL REFERENCES cards(id) ON DELETE CASCADE,
    rating      SMALLINT NOT NULL CHECK (rating BETWEEN 1 AND 4),
    reviewed_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_review_logs_card_id_reviewed_at ON review_logs(card_id, reviewed_at);