create table currencies (
  id BIGINT primary key,
  currency_code varchar(3) not null,
  currency_name varchar(255) not null,
  symbol varchar(255) not null
);

create table invoice_items (
  id BIGINT primary key,
  description varchar(255),
  price numeric(15, 2),
  quantity int8,
  unit_cost numeric(15, 2),
  invoice_id int8
);

create table invoices (
  id BIGINT primary key,
  invoice_date timestamp not null,
  notes varchar(255),
  subtotal numeric(15, 2),
  tax Decimal(15,2) default '0.00' not null,
  total numeric(15, 2) not null,
  currency_id int8,
  recipient_id int8
);

create table recipients (
  id BIGINT primary key,
  address varchar(255) not null,
  email varchar(255),
  name varchar(255) not null,
  phone_number varchar(255) not null
);

alter table invoice_items add constraint FK_IVOICE_ITEMS_INVOICE_ID foreign key (invoice_id) references invoices;
alter table invoices add constraint FK_INVOICES_CURRENCY_ID foreign key (currency_id) references currencies;
alter table invoices add constraint FK_INVOICES_RECIPIENT_ID foreign key (recipient_id) references recipients;