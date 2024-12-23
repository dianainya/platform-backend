ALTER TABLE IF EXISTS platform_prisoner
DROP COLUMN IF EXISTS is_active;

CREATE TABLE IF NOT EXISTS platform_active_floor( active_floor INTEGER);