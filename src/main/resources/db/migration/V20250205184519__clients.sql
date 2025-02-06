create schema if not exists main;

create table if not exists main.clients
(
    id   bigserial primary key,
    name varchar not null
);