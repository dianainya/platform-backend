CREATE TABLE IF NOT EXISTS prisoner
(
    id            UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    first_name    VARCHAR(255) NOT NULL,
    patronymic    VARCHAR(255),
    last_name     VARCHAR(255) NOT NULL,
    weight        FLOAT        NOT NULL,
    birth_date    DATE         NOT NULL,
    passport      TEXT UNIQUE  NOT NULL,
    favorite_dish UUID REFERENCES dish ON DELETE CASCADE
);

