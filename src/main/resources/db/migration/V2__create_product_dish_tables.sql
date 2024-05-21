CREATE TABLE IF NOT EXISTS product
(
    id               UUID  DEFAULT gen_random_uuid() PRIMARY KEY,
    name             VARCHAR(255) UNIQUE NOT NULL,
    calories         INTEGER      NOT NULL,
    proteins         INTEGER      NOT NULL,
    fats             INTEGER      NOT NULL,
    carbohydrates    INTEGER      NOT NULL,
    available_weight FLOAT DEFAULT 0
    );

CREATE TABLE IF NOT EXISTS dish
(
    id          UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name        VARCHAR(255) UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS dish_receipt
(
    product_id UUID REFERENCES product (id) ON DELETE CASCADE,
    dish_id    UUID REFERENCES dish (id) ON DELETE CASCADE,
    weight     INTEGER NOT NULL,
    amount     INTEGER DEFAULT 1,
    PRIMARY KEY (product_id, dish_id)
);