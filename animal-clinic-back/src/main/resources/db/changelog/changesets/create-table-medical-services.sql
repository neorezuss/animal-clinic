--liquibase formatted sql
--changeset aliaksei:create-table-medical-services
CREATE TABLE medical_services
(
    id                      BIGSERIAL PRIMARY KEY,
    medical_service_type_id BIGINT NOT NULL,
    specialist_id           BIGINT NOT NULL,
    pet_id                  BIGINT,
    date                    DATE   NOT NULL,
    time                    time   NOT NULL,
    result                  VARCHAR
);
--rollback DROP TABLE medical_services