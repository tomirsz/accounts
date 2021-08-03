CREATE TABLE IF NOT EXISTS customers(
    id serial primary key,
    first_name varchar(256) not null,
    last_name varchar(256) not null
);

CREATE TABLE IF NOT EXISTS accounts(
    id serial primary key,
    nrb varchar(256) not null,
    currency varchar(6) not null,
    founds numeric(10,2) not null default 0.00,
    customer_id serial
);
ALTER TABLE accounts
    ADD CONSTRAINT accounts_customer_fk
        FOREIGN KEY (customer_id) REFERENCES customers (id);