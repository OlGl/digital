--liquibase formatted sql
--changeset OlgaGlavdel:items-insert-data

INSERT INTO items (name, price, description)
VALUES ('Milk', '1.76', '2.6% Minskaya Marka');

INSERT INTO items (name, price, description)
VALUES ('Chokolate', '3.14', 'Milka');

INSERT INTO items (name, price)
VALUES ('Potato', '2.50');



--rollback delete from items where name='Milk';
--rollback delete from items where name='Chokolate';
--rollback delete from items where name='Potato';