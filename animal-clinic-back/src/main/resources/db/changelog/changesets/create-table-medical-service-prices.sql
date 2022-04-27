--liquibase formatted sql
--changeset aliaksei:create-table-medical-service-prices
CREATE TABLE medical_service_prices
(
    id                      BIGSERIAL PRIMARY KEY,
    medical_service_type_id BIGINT  NOT NULL,
    pet_type_id             BIGINT  NOT NULL,
    price                   NUMERIC NOT NULL,
    UNIQUE (medical_service_type_id, pet_type_id)
);
--rollback DROP TABLE medical_service_prices
