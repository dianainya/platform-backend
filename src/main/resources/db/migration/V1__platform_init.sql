CREATE TABLE IF NOT EXISTS "platform_user"
(
    user_id   UUID    DEFAULT gen_random_uuid() PRIMARY KEY,
    username  VARCHAR(50) UNIQUE    NOT NULL,
    password  VARCHAR(100)          NOT NULL,
    activated BOOLEAN DEFAULT false NOT NULL
);

CREATE TABLE IF NOT EXISTS "platform_role"
(
    name VARCHAR(50) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS "user_role"
(
    user_id  UUID REFERENCES platform_user (user_id),
    role VARCHAR(50) REFERENCES platform_role (name),
    PRIMARY KEY (user_id, role)
);