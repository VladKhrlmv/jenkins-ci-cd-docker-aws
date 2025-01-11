-- V2__update_users_table.sql

-- Add columns to the users table for better integration and additional fields
ALTER TABLE users
    ADD COLUMN keycloak_id UUID UNIQUE,
    ADD COLUMN last_login TIMESTAMP;

-- Example of inserting a Keycloak user
-- INSERT INTO users (user_id, email, name, password_hash, role, keycloak_id, last_login)
-- VALUES (1, 'example@example.com', 'Example User', 'hashed_password', 'customer', 'some-uuid', now());
