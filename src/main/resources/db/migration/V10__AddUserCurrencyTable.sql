create sequence user_currencies_id_seq;

CREATE TABLE user_currencies(
    user_id int8,
    currency_id int8,
    PRIMARY KEY(user_id, currency_id),
    CONSTRAINT FK_USER_CURRENCIES_USER_ID FOREIGN KEY (user_id) references users(id),
    CONSTRAINT FK_USER_CURRENCIES_CURRENCY_ID FOREIGN KEY (currency_id) references currencies(id)
);

--alter table user_currencies add constraint FK_USER_CURRENCIES_USER_ID foreign key (user_id) references users;
--alter table user_currencies add constraint FK_USER_CURRENCIES_CURRENCY_ID foreign key (currency_id) references currencies;