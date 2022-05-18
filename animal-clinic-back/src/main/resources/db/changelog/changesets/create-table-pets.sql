--liquibase formatted sql
--changeset aliaksei:create-table-pets
CREATE TABLE pets
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR NOT NULL,
    user_id     BIGINT  NOT NULL REFERENCES users,
    pet_type_id BIGINT  NOT NULL REFERENCES pets,
    breed       VARCHAR NOT NULL,
    birth_date  DATE    NOT NULL
);
--rollback DROP TABLE pets
