--liquibase formatted sql
--changeset OlgaGlavdel:users-create-table

CREATE TABLE users
(
    id            bigserial NOT NULL,
    username      varchar   NOT NULL UNIQUE,
    email         varchar   NOT NULL UNIQUE,
    date_birth    date      NOT NULL,
    role          role      NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

