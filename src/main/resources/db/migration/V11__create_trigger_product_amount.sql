ALTER TABLE product_warehouse
    ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
DROP CONSTRAINT IF EXISTS product_warehouse_amount_check,
    ADD CONSTRAINT product_warehouse_amount_check CHECK (amount >= 0);

CREATE OR REPLACE FUNCTION insert_product_to_warehouse()
    RETURNS TRIGGER AS
$$
BEGIN
INSERT INTO product_warehouse (product_id, amount)
VALUES (NEW.id, 0);

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_insert_product_to_warehouse ON product;

CREATE TRIGGER trg_insert_product_to_warehouse
    AFTER INSERT
    ON product
    FOR EACH ROW
    EXECUTE FUNCTION insert_product_to_warehouse();


