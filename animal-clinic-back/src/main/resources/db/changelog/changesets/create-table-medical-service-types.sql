--liquibase formatted sql
--changeset aliaksei:create-table-medical-service-types
CREATE TABLE medical_service_types
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    image TEXT NOT NULL,
    short_description VARCHAR NOT NULL,
    long_description TEXT NOT NULL
);
--rollback DROP TABLE medical_service_types
