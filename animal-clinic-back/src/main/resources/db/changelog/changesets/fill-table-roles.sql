--liquibase formatted sql
--changeset aliaksei:fill-table-roles
INSERT INTO roles(name)
VALUES ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN');
