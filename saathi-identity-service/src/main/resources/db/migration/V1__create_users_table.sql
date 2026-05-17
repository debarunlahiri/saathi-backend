create table if not exists users (
    id uuid primary key,
    full_name varchar(150) not null,
    mobile_number varchar(20) not null unique,
    email varchar(150),
    password_hash varchar(255),
    role varchar(30) not null,
    account_status varchar(30) not null default 'ACTIVE',
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone not null default now()
);
