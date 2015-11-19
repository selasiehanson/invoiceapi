alter table invoices
    add column user_id bigint not null;

alter table clients
    add column user_id bigint not null;

alter table invoices add constraint FK_INVOICES_USER_ID foreign key (user_id) references users;
alter table clients  add constraint FK_CLIENTS_USER_ID  foreign key (user_id) references users;