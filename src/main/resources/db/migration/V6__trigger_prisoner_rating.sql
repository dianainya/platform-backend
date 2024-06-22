CREATE OR REPLACE FUNCTION add_prisoner_rating()
RETURNS TRIGGER AS $$
BEGIN
INSERT INTO prisoner_rating (prisoner_id, score)
VALUES (NEW.id, 100);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_prisoner_insert
    AFTER INSERT ON prisoner
    FOR EACH ROW
    EXECUTE FUNCTION add_prisoner_rating();
