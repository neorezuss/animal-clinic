--liquibase formatted sql
--changeset aliaksei:create-table-user_role
CREATE TABLE user_role
(
    user_id BIGINT REFERENCES users,
    role_id BIGINT REFERENCES roles,
    PRIMARY KEY (user_id, role_id)
);
--rollback DROP TABLE user_role



