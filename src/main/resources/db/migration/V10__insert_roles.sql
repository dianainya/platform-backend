insert into platform_role
values ('ADMIN'),
       ('COOK'),
       ('REGISTER'),
       ('ANALYTIC'),
       ('PLARFORM_MANAGER'),
       ('PRISONER')
    ON CONFLICT DO NOTHING ;