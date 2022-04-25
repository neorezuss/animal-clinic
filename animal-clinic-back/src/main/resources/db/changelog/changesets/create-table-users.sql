--liquibase formatted sql
--changeset aliaksei:create-table-users
CREATE TABLE users
(
    id           BIGSERIAL PRIMARY KEY,
    first_name   VARCHAR NOT NULL,
    last_name    VARCHAR NOT NULL,
    patronymic   VARCHAR NOT NULL,
    gender       VARCHAR NOT NULL,
    birth_date   DATE    NOT NULL,
    phone_number VARCHAR NOT NULL,
    email        VARCHAR NOT NULL UNIQUE,
    enabled      BOOLEAN NOT NULL DEFAULT true
);
--rollback DROP TABLE users
