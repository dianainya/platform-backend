INSERT INTO platform_active_floor (active_floor)
SELECT  0
    WHERE NOT EXISTS (SELECT * FROM platform_active_floor);