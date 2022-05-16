--liquibase formatted sql
--changeset aliaksei:create-table-specialists
CREATE TABLE specialists
(
    id           BIGSERIAL PRIMARY KEY,
    first_name   VARCHAR NOT NULL,
    last_name    VARCHAR NOT NULL,
    patronymic   VARCHAR NOT NULL,
    specialty    VARCHAR NOT NULL,
    birth_date   DATE    NOT NULL,
    phone_number VARCHAR NOT NULL,
    address      VARCHAR NOT NULL
);
--rollback DROP TABLE specialists
