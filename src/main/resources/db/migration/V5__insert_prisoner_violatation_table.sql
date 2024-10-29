INSERT INTO prisoner_violation (code, name, score)
VALUES ('MURDER', 'Убийство', 50),
       ('SKIPPING_MEAL', 'Пропуск приема пищи', 5),
       ('IDEOLOGICAL_AGITATION', 'Идеалогическая агитация', 25)
ON CONFLICT(code) DO UPDATE SET score = EXCLUDED.score, name = EXCLUDED.name;