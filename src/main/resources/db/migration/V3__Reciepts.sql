drop sequence if exists receipts_idreceipts_seq;
create sequence receipts_idreceipts_seq;

create table receipts (
    id BIGSERIAL primary key,
    name varchar(255) not null
);