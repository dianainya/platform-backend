CREATE TABLE IF NOT EXISTS "product_warehouse"
(
    product_id UUID REFERENCES product (id) ON DELETE CASCADE,
    amount     NUMERIC CHECK ( amount > 0 )

    );