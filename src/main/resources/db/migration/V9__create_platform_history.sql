CREATE TABLE IF NOT EXISTS "platform_history"
(
    platform_id     UUID REFERENCES platform ON DELETE CASCADE,
    floor           INTEGER PRIMARY KEY,
    first_prisoner  UUID REFERENCES prisoner ON DELETE CASCADE NOT NULL,
    second_prisoner UUID REFERENCES prisoner ON DELETE CASCADE,
    startDate  DATE DEFAULT current_date NOT NULL,
    endDate    DATE
);