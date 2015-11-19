drop sequence if exists user_companies_id_seq;
create sequence user_companies_id_seq;

create table user_companies(
    id bigserial primary key,
    name varchar(50) not null,
    email varchar(50) not null,
    phone_number varchar(15) not null,
    address varchar(100) not null,
    user_id bigint not null
);

alter table user_companies add constraint FK_USER_COMPANIES_USER_ID foreign key (user_id) references users;