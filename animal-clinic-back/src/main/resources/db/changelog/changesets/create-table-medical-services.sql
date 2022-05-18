--liquibase formatted sql
--changeset aliaksei:create-table-medical-services
CREATE TABLE medical_services
(
    id                      BIGSERIAL PRIMARY KEY,
    medical_service_type_id BIGINT  NOT NULL REFERENCES medical_service_types,
    specialist_id           BIGINT REFERENCES specialists,
    pet_id                  BIGINT REFERENCES pets,
    date                    DATE    NOT NULL,
    time                    time    NOT NULL,
    office_number           INTEGER NOT NULL,
    result                  VARCHAR
);
--rollback DROP TABLE medical_services
