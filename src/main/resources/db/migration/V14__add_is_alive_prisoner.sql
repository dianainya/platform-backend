ALTER TABLE IF EXISTS prisoner
    ADD COLUMN IF NOT EXISTS IS_ALIVE BOOLEAN NOT NULL DEFAULT TRUE;