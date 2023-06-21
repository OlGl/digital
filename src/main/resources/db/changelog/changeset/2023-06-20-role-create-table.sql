--liquibase formatted sql
--changeset OlgaGlavdel:role-create-table

CREATE TYPE role AS ENUM ('ADMIN', 'USER');