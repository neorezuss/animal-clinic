--liquibase formatted sql
--changeset aliaksei:create-table-pet-types
CREATE TABLE pet-types
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE
);
--rollback DROP TABLE pet-types
