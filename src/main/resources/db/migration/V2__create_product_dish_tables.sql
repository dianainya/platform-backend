CREATE TABLE IF NOT EXISTS product
(
    id               UUID  DEFAULT gen_random_uuid() PRIMARY KEY,
    name             VARCHAR(255) UNIQUE NOT NULL,
    calories         INTEGER      NOT NULL,
    proteins         INTEGER      NOT NULL,
    fats             INTEGER      NOT NULL,
    carbohydrates    INTEGER      NOT NULL,
    weight           INTEGER      NOT NULL
);

CREATE TABLE IF NOT EXISTS dish
(
    id          UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name        VARCHAR(255) UNIQUE NOT NULL,
    receipt     TEXT NOT NULL,
    description TEXT,
    created_at timestamp default current_timestamp,
    updated_at timestamp
);

CREATE TABLE IF NOT EXISTS dish_ingredients
(
    product_id UUID REFERENCES product (id) ON DELETE CASCADE,
    dish_id    UUID REFERENCES dish (id) ON DELETE CASCADE,
    amount     INTEGER DEFAULT 1,
    PRIMARY KEY (product_id, dish_id)
);