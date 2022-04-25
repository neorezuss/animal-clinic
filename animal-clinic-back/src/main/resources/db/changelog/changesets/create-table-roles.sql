--liquibase formatted sql
--changeset aliaksei:create-table-roles
CREATE TABLE roles
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);
--rollback DROP TABLE roles
