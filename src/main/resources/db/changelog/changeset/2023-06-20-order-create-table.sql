--liquibase formatted sql
--changeset OlgaGlavdel:items-create-table

CREATE TABLE items
(
    id          bigserial NOT NULL,
    name        varchar   NOT NULL,
    price       numeric   NOT NULL,
    description varchar(255),
    is_remove boolean DEFAULT FALSE,
    CONSTRAINT items_pkey PRIMARY KEY (id),
    CONSTRAINT items_price CHECK ( price > 0)
);

