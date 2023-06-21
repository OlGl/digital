--liquibase formatted sql
--changeset OlgaGlavdel:users-insert-data

INSERT INTO users (username, email, date_birth, role)
VALUES ('OlgaAdmin11', 'pin007@tut.by', '2009-09-09', 'ROLE_ADMIN');

INSERT INTO users (username, email, date_birth, role)
VALUES ('username11', 'olga.glavdel@gmail.com', '1999-01-11', 'ROLE_USER');

--rollback delete from users where username='OlgaAdmin11';
--rollback delete from users where username='username11';