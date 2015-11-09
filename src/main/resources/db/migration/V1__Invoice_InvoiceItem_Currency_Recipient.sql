create sequence invoices_id_seq;
create sequence currencies_id_seq;
create sequence invoice_items_id_seq;
create sequence recipients_id_seq;

CREATE TABLE currencies (
  id BIGSERIAL PRIMARY key,
  country varchar(200) NOT NULL,
  iso_alpha2 varchar(2) DEFAULT NULL,
  iso_alpha3 varchar(3) DEFAULT NULL,
  iso_numeric integer,
  currency_code varchar(3) DEFAULT NULL,
  currency_name varchar(32) DEFAULT NULL,
  symbol varchar(3) DEFAULT NULL
);

create table invoice_items (
  id BIGSERIAL primary key,
  description varchar(255),
  price numeric(15, 2),
  quantity int8,
  unit_cost numeric(15, 2),
  invoice_id int8
);

create table invoices (
  id BIGSERIAL primary key,
  invoice_date timestamp not null,
  notes varchar(255),
  subtotal numeric(15, 2),
  tax Decimal(15,2) default '0.00' not null,
  total numeric(15, 2) not null,
  currency_id int8,
  recipient_id int8
);

create table recipients (
  id BIGSERIAL primary key,
  address varchar(255) not null,
  email varchar(255),
  name varchar(255) not null,
  phone_number varchar(255) not null
);

alter table invoice_items add constraint FK_IVOICE_ITEMS_INVOICE_ID foreign key (invoice_id) references invoices;
alter table invoices add constraint FK_INVOICES_CURRENCY_ID foreign key (currency_id) references currencies;
alter table invoices add constraint FK_INVOICES_RECIPIENT_ID foreign key (recipient_id) references recipients;