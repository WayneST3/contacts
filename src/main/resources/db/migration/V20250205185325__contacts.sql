create table if not exists main.contacts
(
    id        bigserial primary key,
    type      varchar not null,
    value     varchar not null,
    client_id bigint  not null,
    constraint contacts_client_fk foreign key (client_id)
        references main.clients (id) match simple
        on update cascade
        on delete cascade
);
