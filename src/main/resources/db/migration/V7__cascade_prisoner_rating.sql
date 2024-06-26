ALTER TABLE prisoner_rating
DROP CONSTRAINT IF EXISTS prisoner_rating_prisoner_id_fkey;

ALTER TABLE prisoner_rating
    ADD CONSTRAINT prisoner_rating_prisoner_id_fkey
        FOREIGN KEY (prisoner_id)
            REFERENCES prisoner
            ON DELETE CASCADE;
