-- Create the function if it doesn't exist
CREATE OR REPLACE FUNCTION add_prisoner_rating()
    RETURNS TRIGGER AS $$
BEGIN
INSERT INTO prisoner_rating (prisoner_id, score)
VALUES (NEW.id, 100);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Check if the trigger already exists
DO $$
BEGIN
        IF NOT EXISTS (
            SELECT 1
            FROM pg_trigger
                     JOIN pg_class ON pg_trigger.tgrelid = pg_class.oid
            WHERE pg_trigger.tgname = 'after_prisoner_insert'
              AND pg_class.relname = 'prisoner'
        ) THEN
-- Create the trigger if it doesn't exist
CREATE TRIGGER after_prisoner_insert
    AFTER INSERT ON prisoner
    FOR EACH ROW
    EXECUTE FUNCTION add_prisoner_rating();
END IF;
END $$;
