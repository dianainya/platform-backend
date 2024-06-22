CREATE TABLE IF NOT EXISTS platform_prisoner
(
    floor           INTEGER PRIMARY KEY,
    first_prisoner  UUID REFERENCES prisoner ON DELETE CASCADE NOT NULL,
    second_prisoner UUID REFERENCES prisoner ON DELETE CASCADE,
    is_active       BOOLEAN DEFAULT FALSE                      NOT NULL
);

CREATE TABLE IF NOT EXISTS prisoner_rating
(
    prisoner_id UUID REFERENCES prisoner,
    score NUMERIC DEFAULT 100 NOT NULL
);

CREATE TABLE IF NOT EXISTS prisoner_violation
(
    code VARCHAR(255) PRIMARY KEY,
    name TEXT NOT NULL,
    score NUMERIC DEFAULT 5 NOT NULL
);

