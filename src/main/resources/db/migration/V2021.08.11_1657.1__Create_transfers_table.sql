CREATE TABLE IF NOT EXISTS transfers(
    id serial primary key,
    sender_account varchar(256) not null,
    recipient_account varchar(256) not null,
    title varchar(256) not null,
    amount numeric(10,2) not null
);