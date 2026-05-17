create table if not exists customer_profiles (
    id uuid primary key,
    identity_user_id uuid not null unique,
    full_name varchar(150) not null,
    mobile_number varchar(20) not null,
    email varchar(150),
    default_address varchar(500),
    default_latitude numeric(10,7),
    default_longitude numeric(10,7),
    emergency_contact varchar(20),
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone not null default now()
);

create unique index if not exists idx_customer_identity_user on customer_profiles(identity_user_id);
