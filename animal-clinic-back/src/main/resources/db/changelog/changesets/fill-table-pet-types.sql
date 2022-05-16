--liquibase formatted sql
--changeset aliaksei:fill-table-pet-types
INSERT INTO pet_types(name)
VALUES ('CAT'),
       ('DOG'),
       ('BIRD');
