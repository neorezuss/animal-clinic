--liquibase formatted sql
--changeset aliaksei:create-table-passwords
CREATE TABLE passwords
(
    user_id  BIGINT PRIMARY KEY REFERENCES users,
    password VARCHAR NOT NULL
);
--rollback DROP TABLE passwords
