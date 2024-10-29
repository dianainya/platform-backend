CREATE TABLE IF NOT EXISTS platform
(
    id UUID    DEFAULT gen_random_uuid() PRIMARY KEY,
    code VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    floor_amount INTEGER NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE
);


CREATE TABLE IF NOT EXISTS platform_prisoner
(
    platform_id     UUID REFERENCES platform ON DELETE CASCADE,
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

insert into platform (id, code, name, floor_amount) values ('16d34177-1436-4ea1-9236-74bd8e113a9d','CITADEL', 'Цитадель', 100);