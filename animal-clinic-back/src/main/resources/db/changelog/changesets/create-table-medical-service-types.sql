--liquibase formatted sql
--changeset aliaksei:create-table-medical-service-types
CREATE TABLE medical_service_types
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    image BYTEA NOT NULL,
    short_description VARCHAR NOT NULL,
    long_description VARCHAR NOT NULL
);
--rollback DROP TABLE medical_service_types
