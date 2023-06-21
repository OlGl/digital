--liquibase formatted sql
--changeset OlgaGlavdel:users-create-table

CREATE TABLE users
(
    id            bigserial NOT NULL,
    username      varchar   NOT NULL UNIQUE,
    email         varchar   NOT NULL UNIQUE,
    date_birth    date      NOT NULL,
    role       varchar   NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user_role FOREIGN KEY (role)
        REFERENCES roles (name)
);

