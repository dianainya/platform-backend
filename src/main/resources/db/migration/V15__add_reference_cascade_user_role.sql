ALTER TABLE IF EXISTS user_role
DROP CONSTRAINT IF EXISTS user_role_user_id_fkey,
    DROP CONSTRAINT IF EXISTS user_role_role_fkey;

ALTER TABLE user_role
    ADD CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES platform_user (user_id) ON DELETE CASCADE;

ALTER TABLE user_role
    ADD CONSTRAINT user_role_role_fkey FOREIGN KEY (role)
        REFERENCES platform_role (name) ON DELETE CASCADE;
