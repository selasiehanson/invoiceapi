drop sequence if exists usersid_seq;
create sequence usersid_seq;

create table users (
    id bigserial primary key,
    account_enabled boolean not null,
    account_expired boolean not null,
    account_locked boolean not null,
    credentials_expired boolean not null,
    password varchar(100) not null,
    username varchar(30) not null
);

create table user_authorities (
    authority varchar(255) not null,
    user_id bigint not null,
    primary key (user_id, authority)
);

alter table users add constraint UK_USERS_USERNAME unique (username);
alter table user_authorities add constraint FK_USER_AUTHORITIES_USER_ID foreign key (user_id) references users;